package com.junktion.api.v1.services

import com.junktion.api.v1.models.repositories.ImageRepository
import io.kotlintest.TestCase
import io.kotlintest.specs.StringSpec
import io.mockk.mockkClass
import io.mockk.verify

fun mockImageRepository() = mockkClass(ImageRepository::class, relaxed = true)
fun instantiate(imageRepository: ImageRepository) = ImageService(imageRepository)

class ImageServiceTest : StringSpec() {

	private lateinit var imageService: ImageService
	private lateinit var imageRepository: ImageRepository

	private fun upload() = imageService.upload()

	override fun beforeTest(testCase: TestCase) {
		imageRepository = mockImageRepository()
		imageService = instantiate(imageRepository)
	}

	init {
		"${ImageRepository::register}を実行すること" {
			upload()
			verify(exactly = 1) { imageRepository.register() }
		}
	}
}