package junktion.v1.infrastructure

import io.kotlintest.TestCase
import io.kotlintest.specs.StringSpec
import io.mockk.every
import io.mockk.mockkClass
import junktion.v1.api.InfrastructureException
import junktion.v1.core.Image
import junktion.v1.mybatis.ImageMapper
import org.apache.ibatis.exceptions.PersistenceException
import org.assertj.core.api.Assertions.assertThatThrownBy

fun mockImageMapper() = mockkClass(ImageMapper::class, relaxed = true)

class ImageRepositoryImplTest : StringSpec() {

	private lateinit var imageRepository: ImageRepositoryImpl
	private lateinit var imageMapper: ImageMapper

	override fun beforeTest(testCase: TestCase) {
		imageMapper = mockImageMapper()
		imageRepository = ImageRepositoryImpl(imageMapper)
	}

	init {
		"問題なく実行されること" {
			val image = Image("tag", ByteArray(0))
			imageRepository.register(image)
		}
		"永続化に関する例外が発生した場合に例外がインフラ関連例外としてラップされること" {
			every { imageMapper.save(any()) } throws PersistenceException()
			val image = Image("tag", ByteArray(0))
			assertThatThrownBy { imageRepository.register(image) }
					.isInstanceOf(InfrastructureException::class.java)
		}
	}
}