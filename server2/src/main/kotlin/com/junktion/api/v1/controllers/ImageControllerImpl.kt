package com.junktion.api.v1.controllers

import com.junktion.api.v1.models.image.ImageController
import com.junktion.api.v1.models.image.ImageService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import javax.validation.ConstraintViolationException

@RestController
class ImageControllerImpl (
        private val imageService: ImageService
): ImageController {

    override fun upload(multipartFile: MultipartFile, tag: String): String {
        return imageService.upload()
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException::class)
    fun handleException(): String {
        return "バリデーションエラーです"
    }
}