package com.junktion.api.v1

import com.junktion.api.v1.models.image.Image
import com.junktion.api.v1.repositories.ImageMapper
import io.kotlintest.extensions.TestListener
import io.kotlintest.specs.StringSpec
import io.kotlintest.spring.SpringListener
import org.assertj.core.api.Assertions.assertThat
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDateTime


@MybatisTest
class ImageMapperTest: StringSpec() {

	override fun listeners(): List<TestListener> = listOf(SpringListener)

	@Autowired
	private lateinit var mapper: ImageMapper

	init {
		"テスト" {
			val image = Image("tag", LocalDateTime.now(), LocalDateTime.now())
			mapper.save(image)
			val list = mapper.selectAll()
			assertThat(list).isNotEmpty
			assertThat(list[0].tag).isEqualTo("tag")
			assertThat(list[0].createdAt).isNotNull()
		}
	}
}