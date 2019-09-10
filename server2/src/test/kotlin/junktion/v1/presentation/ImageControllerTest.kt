package junktion.v1.presentation

import io.kotlintest.TestCase
import io.kotlintest.specs.StringSpec
import io.mockk.every
import io.mockk.mockkClass
import io.mockk.verify
import junktion.v1.core.Image
import junktion.v1.service.ImageServiceImpl
import org.junit.jupiter.api.assertThrows
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
			every { uploadedFile.originalFilename } returns "test.png"
			every { uploadedFile.bytes } returns ByteArray(0)
			upload(uploadedFile, "some_tag")
			verify(exactly = 1) { imageService.upload(Image("some_tag.png", uploadedFile.bytes)) }
		}

		"ファイル名に拡張子がない場合tagのみがファイル名になること" {
			val uploadedFile = mockkClass(MultipartFile::class, relaxed = true)
			every { uploadedFile.originalFilename } returns "test"
			every { uploadedFile.bytes } returns ByteArray(0)
			upload(uploadedFile, "some_tag")
			verify(exactly = 1) { imageService.upload(Image("some_tag", uploadedFile.bytes)) }
		}

		"ファイル名がnullだった場合例外を投げること" {
			val uploadedFile = mockkClass(MultipartFile::class, relaxed = true)
			every { uploadedFile.originalFilename } returns null
			assertThrows<IllegalStateException> { upload(uploadedFile, "some_tag") }
		}
	}
}