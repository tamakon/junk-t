package com.junktion.api.v1.models.controllers

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Api(description = "画像ファイルAPI")
@RequestMapping("api/v1/images")
interface ImageController {

	@GetMapping("upload")
	@ApiOperation(value = "画像ファイルを登録API")
	fun upload(): String
}