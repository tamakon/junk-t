package com.junktion.api.v1.services

import com.junktion.api.v1.models.image.Image
import com.junktion.api.v1.models.image.ImageRepository
import com.junktion.api.v1.models.image.ImageService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ImageServiceImpl(
        private val imageRepository: ImageRepository
): ImageService {

    @PreAuthorize("#oauth2.hasScope('default')")
    override fun upload(): String {
        val image = Image("tag", LocalDateTime.now(), LocalDateTime.now())
        imageRepository.register(image)
        return "TODO"
    }
}