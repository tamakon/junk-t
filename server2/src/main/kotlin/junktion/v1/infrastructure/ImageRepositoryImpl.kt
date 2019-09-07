package junktion.v1.infrastructure

import junktion.v1.core.Image
import junktion.v1.api.ImageRepository
import junktion.v1.mybatis.ImageMapper
import org.springframework.stereotype.Repository

@Repository
class ImageRepositoryImpl(
        private val imageMapper: ImageMapper
): ImageRepository {

    override fun register(image: Image) {
        imageMapper.save(image)
    }
}