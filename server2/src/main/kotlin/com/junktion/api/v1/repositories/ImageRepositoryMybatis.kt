package com.junktion.api.v1.repositories

import com.junktion.api.v1.models.image.ImageRepository
import org.springframework.stereotype.Repository

@Repository
class ImageRepositoryMybatis: ImageRepository {

    override fun register() {
    }
}