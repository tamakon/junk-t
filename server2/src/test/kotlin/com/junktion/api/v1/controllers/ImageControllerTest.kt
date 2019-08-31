package com.junktion.api.v1.controllers

import com.junktion.api.v1.services.ImageServiceImpl
import io.kotlintest.TestCase
import io.kotlintest.specs.StringSpec
import io.mockk.mockkClass
import io.mockk.verify
import org.springframework.web.multipart.MultipartFile

fun mockImageService() = mockkClass(ImageServiceImpl::class, relaxed = true)
fun instantiate(imageService: ImageServiceImpl) = ImageControllerImpl(imageService)

class ImageControllerImplTest: StringSpec() {

	private lateinit var imageController: ImageControllerImpl
	private lateinit var imageService: ImageServiceImpl

	private fun upload(multipartFile: MultipartFile, tag: String) = imageController.upload(multipartFile, tag)

	override fun beforeTest(testCase: TestCase) {
		imageService = mockImageService()
		imageController = instantiate(imageService)
	}

	init {
		"imageService#uploadを実行すること" {
			val uploadedFile = mockkClass(MultipartFile::class, relaxed = true)
			upload(uploadedFile, "some_tag")
			verify(exactly = 1) { imageService.upload() }
		}
	}
}