package com.junktion.api.v1.repositories

import com.junktion.api.v1.models.image.Image
import com.junktion.api.v1.models.image.ImageRepository
import io.kotlintest.TestCase
import io.kotlintest.specs.StringSpec
import io.mockk.mockkClass
import java.time.LocalDateTime

fun mockImageMapper() = mockkClass(ImageMapper::class, relaxed = true)

class ImageRepositoryMyBatisTest : StringSpec() {

	private lateinit var imageRepository: ImageRepository

	override fun beforeTest(testCase: TestCase) {
		imageRepository = ImageRepositoryMybatis(mockImageMapper())
	}

	init {
		"問題なく実行されること" {
			val image = Image("tag", LocalDateTime.now(), LocalDateTime.now())
			imageRepository.register(image)
		}
	}
}