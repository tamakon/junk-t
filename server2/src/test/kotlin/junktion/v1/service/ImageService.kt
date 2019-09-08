package junktion.v1.service

import io.kotlintest.TestCase
import io.kotlintest.specs.StringSpec
import io.mockk.mockkClass
import io.mockk.verify
import junktion.v1.api.ImageFileStorage
import junktion.v1.api.ImageRepository
import utils.TEST_IMAGE_FILE

fun mockImageRepository() = mockkClass(ImageRepository::class, relaxed = true)
fun mockImageFileStorage() = mockkClass(ImageFileStorage::class, relaxed = true)
fun instantiate(imageRepository: ImageRepository, imageFileStorage: ImageFileStorage)
		= ImageServiceImpl(imageRepository, imageFileStorage)

class ImageServiceImplTest : StringSpec() {

	private lateinit var imageService: ImageServiceImpl
	private lateinit var imageFileStorage: ImageFileStorage
	private lateinit var imageRepository: ImageRepository

	override fun beforeTest(testCase: TestCase) {
		imageRepository = mockImageRepository()
		imageFileStorage = mockImageFileStorage()
		imageService = instantiate(imageRepository, imageFileStorage)
	}

	init {
		"${ImageRepository::register}と${ImageFileStorage::save}を実行すること" {
			imageService.upload(TEST_IMAGE_FILE, "tag")
			verify(exactly = 1) { imageRepository.register(any()) }
			verify(exactly = 1) { imageFileStorage.save(TEST_IMAGE_FILE, "tag") }
		}
	}
}