package com.junktion.api.v1.controllers

import com.junktion.api.v1.models.controllers.ImageController
import com.junktion.api.v1.services.ImageService
import org.springframework.web.bind.annotation.RestController

@RestController
class ImageControllerDefault (
        private val imageService: ImageService
): ImageController {

    override fun upload(): String {
        return imageService.upload()
    }
}