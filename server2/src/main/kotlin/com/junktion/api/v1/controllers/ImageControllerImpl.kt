package com.junktion.api.v1.controllers

import com.junktion.api.v1.models.image.ImageController
import com.junktion.api.v1.models.image.ImageService
import org.springframework.web.bind.annotation.RestController

@RestController
class ImageControllerImpl (
        private val imageService: ImageService
): ImageController {

    override fun upload(): String {
        return imageService.upload()
    }
}