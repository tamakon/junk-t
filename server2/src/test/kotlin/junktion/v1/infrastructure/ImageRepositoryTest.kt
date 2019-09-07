package junktion.v1.infrastructure

import junktion.v1.core.Image
import junktion.v1.api.ImageRepository
import junktion.v1.mybatis.ImageMapper
import io.kotlintest.TestCase
import io.kotlintest.specs.StringSpec
import io.mockk.mockkClass
import java.time.LocalDateTime

fun mockImageMapper() = mockkClass(ImageMapper::class, relaxed = true)

class ImageRepositoryMyBatisTest : StringSpec() {

	private lateinit var imageRepository: ImageRepository

	override fun beforeTest(testCase: TestCase) {
		imageRepository = ImageRepositoryImpl(mockImageMapper())
	}

	init {
		"問題なく実行されること" {
			val image = Image("tag", LocalDateTime.now(), LocalDateTime.now())
			imageRepository.register(image)
		}
	}
}