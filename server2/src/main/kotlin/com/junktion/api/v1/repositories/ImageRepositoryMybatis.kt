package com.junktion.api.v1.repositories

import com.junktion.api.v1.models.image.Image
import com.junktion.api.v1.models.image.ImageRepository
import org.springframework.stereotype.Repository

@Repository
class ImageRepositoryMybatis(
        private val imageMapper: ImageMapper
): ImageRepository {

    override fun register(image: Image) {
        imageMapper.save(image)
    }
}