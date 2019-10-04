package junktion.v1.rest

import com.ninjasquad.springmockk.SpykBean
import io.kotlintest.TestCase
import io.kotlintest.extensions.TestListener
import io.kotlintest.specs.StringSpec
import io.kotlintest.spring.SpringListener
import io.mockk.every
import junktion.JunktionAdminConfig
import junktion.Oauth2ClientConfig
import junktion.v1.api.ImageFileStorage
import junktion.v1.api.ImageRepository
import junktion.v1.api.InfrastructureException
import org.assertj.db.api.Assertions.assertThat
import org.assertj.db.type.Changes
import org.assertj.db.type.DateValue
import org.assertj.db.type.Table
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.json.JacksonJsonParser
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.util.LinkedMultiValueMap
import java.io.IOException
import javax.sql.DataSource


private const val UPLOAD_PATH = "/api/v1/images/upload"

@SpringBootTest
@AutoConfigureMockMvc
class ImageControllerIntegrationTest: StringSpec() {

	@Autowired private lateinit var mockMvc: MockMvc
	@Autowired private lateinit var oauth2ClientConfig: Oauth2ClientConfig
	@Autowired private lateinit var junktionAdminConfig: JunktionAdminConfig
	private var accessToken: String? = null

	@Autowired private lateinit var dataSource: DataSource
	@SpykBean private lateinit var imageRepository: ImageRepository
	@SpykBean private lateinit var imageFileStorage: ImageFileStorage

	override fun listeners(): List<TestListener> = listOf(SpringListener)

	override fun beforeTest(testCase: TestCase) {
		accessToken = accessToken ?: obtainAccessToken()
	}

	init {
		"通常アクセスで401になること" {
			val requestWithoutAccessToken = multipart(UPLOAD_PATH)
					.file("upload_file", "test data".toByteArray())
					.param("tag", "some_tag")
			val changes = Changes(Table(dataSource, "images"))

			changes.setStartPointNow()
			mockMvc.perform(requestWithoutAccessToken)
					.andDo(print())
					.andExpect(status().isUnauthorized)
			changes.setEndPointNow()

			assertThat(changes).hasNumberOfChanges(0)
		}
		"認証後アクセスで200になること" {
			val request = multipart(UPLOAD_PATH)
					.file("upload_file", "test data".toByteArray())
					.param("tag", "some_tag")
					.header("Authorization", "Bearer $accessToken")
			val changes = Changes(Table(dataSource, "images"))

			val now = DateValue.now()
			changes.setStartPointNow()
			mockMvc.perform(request)
					.andDo(print())
					.andExpect(status().isOk)
			changes.setEndPointNow()

			assertThat(changes).hasNumberOfChanges(1).change()
					.isCreation
					.column("name").valueAtEndPoint().isEqualTo("some_tag")
					.column("created_at").valueAtEndPoint().isAfter(now)
					.column("updated_at").valueAtEndPoint().isAfter(now)
		}
		"必須要素エラーで400になること(バリデーションではなく必須が前提)" {
			val gettingWithAccessToken = multipart(UPLOAD_PATH)
					.header("Authorization", "Bearer $accessToken")
					.param("tag", "some_tag")
			val changes = Changes(Table(dataSource, "images"))

			changes.setStartPointNow()
			mockMvc.perform(gettingWithAccessToken)
					.andDo(print())
					.andExpect(status().isBadRequest)
			changes.setEndPointNow()

			assertThat(changes).hasNumberOfChanges(0)
		}
		"バリデーション_tagが空文字の場合" {
			val request = multipart(UPLOAD_PATH)
					.file("upload_file", "test data".toByteArray())
					.param("tag", "")
					.header("Authorization", "Bearer $accessToken")
			val changes = Changes(Table(dataSource, "images"))

			changes.setStartPointNow()
			mockMvc.perform(request)
					.andDo(print())
					.andExpect(status().isBadRequest)
			changes.setEndPointNow()

			assertThat(changes).hasNumberOfChanges(0)
		}
		"DB永続化に失敗したらロールバックされること" {
			every { imageRepository.register(any()) } throws InfrastructureException(RuntimeException())
			val request = multipart(UPLOAD_PATH)
					.file("upload_file", "test data".toByteArray())
					.param("tag", "some_tag")
					.header("Authorization", "Bearer $accessToken")
			val changes = Changes(Table(dataSource, "images"))

			changes.setStartPointNow()
			mockMvc.perform(request)
					.andDo(print())
					.andExpect(status().is5xxServerError)
			changes.setEndPointNow()

			assertThat(changes).hasNumberOfChanges(0)
		}
		"ファイル処理に失敗したらロールバックされること" {
			every { imageFileStorage.save(any()) } throws InfrastructureException(IOException())
			val request = multipart(UPLOAD_PATH)
					.file("upload_file", "test data".toByteArray())
					.param("tag", "some_tag")
					.header("Authorization", "Bearer $accessToken")
			val changes = Changes(Table(dataSource, "images"))

			changes.setStartPointNow()
			mockMvc.perform(request)
					.andDo(print())
					.andExpect(status().is5xxServerError)
			changes.setEndPointNow()

			assertThat(changes).hasNumberOfChanges(0)
		}
	}

	private fun obtainAccessToken(): String {
		val params = LinkedMultiValueMap<String, String>().apply {
			add("grant_type", "password")
			add("username", junktionAdminConfig.userId)
			add("password", junktionAdminConfig.password)
		}
		val result = mockMvc.perform(post("/oauth/token")
				.params(params)
				.with(httpBasic(oauth2ClientConfig.clientId, oauth2ClientConfig.clientSecret))
				.accept("application/json;charset=UTF-8"))
				.andExpect(status().isOk)
				.andExpect(content().contentType("application/json;charset=UTF-8"))

		val resultString = result.andReturn().response.contentAsString
		val resultJson = JacksonJsonParser().parseMap(resultString)
		return resultJson["access_token"].toString()
	}
}
