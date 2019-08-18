package com.junktion.api.v1.services

import com.junktion.api.v1.repositories.ImageRepository
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service

@Service
class ImageService(
        private val imageRepository: ImageRepository
) {

    @PreAuthorize("#oauth2.hasScope('default')")
    fun upload(): String {
        imageRepository.register()
        return "TODO"
    }
}