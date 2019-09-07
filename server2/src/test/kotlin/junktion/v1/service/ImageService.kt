package junktion.v1.service

import junktion.v1.api.ImageFileStorage
import junktion.v1.api.ImageRepository
import io.kotlintest.TestCase
import io.kotlintest.specs.StringSpec
import io.mockk.mockkClass
import io.mockk.verify

fun mockImageRepository() = mockkClass(ImageRepository::class, relaxed = true)
fun mockImageFileStorage() = mockkClass(ImageFileStorage::class, relaxed = true)
fun instantiate(imageRepository: ImageRepository, imageFileStorage: ImageFileStorage)
		= ImageServiceImpl(imageRepository, imageFileStorage)

class ImageServiceImplTest : StringSpec() {

	private lateinit var imageService: ImageServiceImpl
	private lateinit var imageFileStorage: ImageFileStorage
	private lateinit var imageRepository: ImageRepository

	private fun upload() = imageService.upload()

	override fun beforeTest(testCase: TestCase) {
		imageRepository = mockImageRepository()
		imageFileStorage = mockImageFileStorage()
		imageService = instantiate(imageRepository, imageFileStorage)
	}

	init {
		"${ImageRepository::register}を実行すること" {
			upload()
			verify(exactly = 1) { imageRepository.register(any()) }
		}
	}
}