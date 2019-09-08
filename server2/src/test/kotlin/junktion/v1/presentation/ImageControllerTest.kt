package junktion.v1.presentation

import io.kotlintest.TestCase
import io.kotlintest.specs.StringSpec
import io.mockk.mockkClass
import io.mockk.verify
import junktion.v1.service.ImageServiceImpl
import org.springframework.web.multipart.MultipartFile

fun mockImageService() = mockkClass(ImageServiceImpl::class, relaxed = true)
fun instantiate(imageService: ImageServiceImpl) = ImageControllerImpl(imageService)

class ImageControllerImplTest: StringSpec() {

	private lateinit var imageController: ImageControllerImpl
	private lateinit var imageService: ImageServiceImpl

	override fun beforeTest(testCase: TestCase) {
		imageService = mockImageService()
		imageController = instantiate(imageService)
	}

	init {
		"imageService#uploadを実行すること" {
			val uploadedFile = mockkClass(MultipartFile::class, relaxed = true)
			imageController.upload(uploadedFile, "some_tag")
			verify(exactly = 1) { imageService.upload(any(), "some_tag") }
		}
	}
}