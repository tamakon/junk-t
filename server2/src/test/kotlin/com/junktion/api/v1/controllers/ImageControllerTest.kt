package com.junktion.api.v1.controllers

import com.junktion.api.v1.services.ImageService
import io.kotlintest.TestCase
import io.kotlintest.specs.StringSpec
import io.mockk.mockkClass
import io.mockk.verify

fun mockImageService() = mockkClass(ImageService::class, relaxed = true)
fun instantiate(imageService: ImageService) = ImageControllerDefault(imageService)

class ImageControllerDefaultTest: StringSpec() {

	private lateinit var imageController: ImageControllerDefault
	private lateinit var imageService: ImageService

	private fun upload() = imageController.upload()

	override fun beforeTest(testCase: TestCase) {
		imageService = mockImageService()
		imageController = instantiate(imageService)
	}

	init {
		"imageService#uploadを実行すること" {
			upload()
			verify(exactly = 1) { imageService.upload() }
		}
	}
}