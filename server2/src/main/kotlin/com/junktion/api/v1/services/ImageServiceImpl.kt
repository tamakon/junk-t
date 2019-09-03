package com.junktion.api.v1.services

import com.junktion.api.v1.models.image.Image
import com.junktion.api.v1.models.image.ImageFileStorage
import com.junktion.api.v1.models.image.ImageRepository
import com.junktion.api.v1.models.image.ImageService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.io.File
import java.time.LocalDateTime

@Service
@Transactional
class ImageServiceImpl(
        private val imageRepository: ImageRepository,
        private val imageFileStorage: ImageFileStorage
): ImageService {

    override fun upload(file: File, tag: String) {
        val image = Image(tag, LocalDateTime.now(), LocalDateTime.now())
        imageRepository.register(image)
        imageFileStorage.save(file, tag)
    }

    @PreAuthorize("#oauth2.hasScope('default')")
    override fun upload(): String {
        val image = Image("tag", LocalDateTime.now(), LocalDateTime.now())
        imageRepository.register(image)
        return "TODO"
    }
}