package junktion.v1.infrastructure

import junktion.v1.api.ImageRepository
import junktion.v1.core.Image
import junktion.v1.mybatis.ImageMapper
import junktion.v1.mybatis.ImageRecord
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class ImageRepositoryImpl(
        private val imageMapper: ImageMapper
): ImageRepository {

    override fun register(image: Image) {
        val now = LocalDateTime.now()
        val imageTable = ImageRecord(image.name, now, now)
        imageMapper.save(imageTable)
    }
}