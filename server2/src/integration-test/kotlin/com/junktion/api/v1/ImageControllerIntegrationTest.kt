package com.junktion.api.v1

import io.kotlintest.extensions.TestListener
import io.kotlintest.specs.StringSpec
import io.kotlintest.spring.SpringListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class ImageControllerIntegrationTest: StringSpec() {

	override fun listeners(): List<TestListener> = listOf(SpringListener)

	@Autowired
	private lateinit var mockMvc: MockMvc

	init {
		"通常アクセスで404になること" {
			mockMvc.perform(get("/api/v1/images/upload"))
					.andDo(print())
					.andExpect(status().isUnauthorized)
		}
	}
}
