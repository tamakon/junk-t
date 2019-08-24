package com.junktion.api.v1

import com.junktion.JunktionAdminConfig
import com.junktion.Oauth2ClientConfig
import io.kotlintest.TestCase
import io.kotlintest.extensions.TestListener
import io.kotlintest.specs.StringSpec
import io.kotlintest.spring.SpringListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.json.JacksonJsonParser
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.util.LinkedMultiValueMap


@SpringBootTest
@AutoConfigureMockMvc
class ImageControllerIntegrationTest: StringSpec() {

	override fun listeners(): List<TestListener> = listOf(SpringListener)

	@Autowired private lateinit var mockMvc: MockMvc
	@Autowired private lateinit var oauth2ClientConfig: Oauth2ClientConfig
	@Autowired private lateinit var junktionAdminConfig: JunktionAdminConfig

	init {
		"通常アクセスで404になること" {
			mockMvc.perform(get("/api/v1/images/upload"))
					.andDo(print())
					.andExpect(status().isUnauthorized)
		}
		"認証後アクセスで200になること" {
			val accessToken = obtainAccessToken()
			val gettingWithAccessToken = get("/api/v1/images/upload")
					.header("Authorization", "Bearer $accessToken")
			mockMvc.perform(gettingWithAccessToken)
					.andDo(print())
					.andExpect(status().isOk)
		}
	}

	override fun beforeTest(testCase: TestCase) {

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
