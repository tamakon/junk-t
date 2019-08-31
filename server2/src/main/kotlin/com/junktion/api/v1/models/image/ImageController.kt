package com.junktion.api.v1.models.image

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile
import javax.validation.constraints.NotEmpty

@Api(description = "画像ファイルAPI")
@RequestMapping("api/v1/images")
@Validated
interface ImageController {

	@PostMapping("upload", consumes = [MULTIPART_FORM_DATA_VALUE])
	@ApiOperation(value = "画像ファイルを登録API")
	fun upload(
			@RequestParam("upload_file")
			multipartFile: MultipartFile,
			@RequestParam("tag")
			@NotEmpty
			tag: String): String
}