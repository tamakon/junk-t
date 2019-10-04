package junktion

import io.kotlintest.extensions.TestListener
import io.kotlintest.specs.StringSpec
import io.kotlintest.spring.SpringListener
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * テストカバレッジ確保のためだけにやってます。
 */
class JunktionApplicationTest {
	@Test
	fun startup() {
		main(arrayOf())
	}
}

/**
 * テストカバレッジ確保のためだけにやってます。
 */
@SpringBootTest
@AutoConfigureMockMvc
class ExampleControllerIntegrationTest: StringSpec() {

	@Autowired private lateinit var mockMvc: MockMvc

	override fun listeners(): List<TestListener> = listOf(SpringListener)

	init {
		"画面が問題なく表示されること" {
			mockMvc.perform(get("/upload_image_example"))
					.andDo(print())
					.andExpect(status().isOk)
		}
	}
}