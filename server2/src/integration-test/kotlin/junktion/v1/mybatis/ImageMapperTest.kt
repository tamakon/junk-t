package junktion.v1.mybatis

import io.kotlintest.extensions.TestListener
import io.kotlintest.specs.StringSpec
import io.kotlintest.spring.SpringListener
import org.assertj.core.api.Assertions.assertThat
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDateTime


@MybatisTest
class ImageMapperTest: StringSpec() {

	override fun listeners(): List<TestListener> = listOf(SpringListener)

	@Autowired
	private lateinit var mapper: ImageMapper

	init {
		"insert処理が正しく行われること" {
			val now = LocalDateTime.now()
			val image = ImageRecord("tag", now, now)
			mapper.save(image)
			val list = mapper.selectAll()
			assertThat(list).isNotEmpty
			assertThat(list[0].name).isEqualTo("tag")
			assertThat(list[0].createdAt).isNotNull()
			assertThat(list[0].updatedAt).isNotNull()
		}
	}
}