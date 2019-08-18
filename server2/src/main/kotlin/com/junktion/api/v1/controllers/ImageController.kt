package com.junktion.api.v1.controllers

import com.junktion.api.v1.services.ImageService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Api(description = "画像ファイルAPI")
@RestController
@RequestMapping("api/v1/images")
class ImageController(
        private val imageService: ImageService
) {

    @GetMapping("upload")
    @ApiOperation(value = "画像ファイルを登録API")
    fun upload(): String {
        return imageService.upload()
    }
}