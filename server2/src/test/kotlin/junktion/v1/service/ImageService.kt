package junktion.v1.service

import io.kotlintest.TestCase
import io.kotlintest.specs.StringSpec
import io.mockk.mockkClass
import io.mockk.verify
import junktion.v1.api.ImageFileStorage
import junktion.v1.api.ImageRepository
import junktion.v1.core.Image

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
			val image = Image("test.png", ByteArray(0))
			imageService.upload(image)
			verify(exactly = 1) { imageRepository.register(image) }
			verify(exactly = 1) { imageFileStorage.save(image) }
		}
	}
}