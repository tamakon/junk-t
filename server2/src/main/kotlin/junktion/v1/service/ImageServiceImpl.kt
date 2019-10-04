package junktion.v1.service

import junktion.v1.api.ImageFileStorage
import junktion.v1.api.ImageRepository
import junktion.v1.api.ImageService
import junktion.v1.core.Image
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ImageServiceImpl(
        private val imageRepository: ImageRepository,
        private val imageFileStorage: ImageFileStorage
): ImageService {
    override fun upload(image: Image) {
        imageRepository.register(image)
        imageFileStorage.save(image)
    }
}