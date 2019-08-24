package com.junktion.api.v1.services

import com.junktion.api.v1.models.image.ImageRepository
import com.junktion.api.v1.models.image.ImageService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service

@Service
class ImageServiceImpl(
        private val imageRepository: ImageRepository
): ImageService {

    @PreAuthorize("#oauth2.hasScope('default')")
    override fun upload(): String {
        imageRepository.register()
        return "TODO"
    }
}