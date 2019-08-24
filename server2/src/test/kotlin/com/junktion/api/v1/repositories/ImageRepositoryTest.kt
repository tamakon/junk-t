package com.junktion.api.v1.repositories

import com.junktion.api.v1.models.repositories.ImageRepository
import io.kotlintest.TestCase
import io.kotlintest.specs.StringSpec

class ImageRepositoryMyBatisTest : StringSpec() {

	private lateinit var imageRepository: ImageRepository

	override fun beforeTest(testCase: TestCase) {
		imageRepository = ImageRepositoryMybatis()
	}

	init {
		"問題なく実行されること" {
			imageRepository.register()
		}
	}
}