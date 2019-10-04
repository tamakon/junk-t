package junktion.v1.presentation

import junktion.v1.api.ImageController
import junktion.v1.api.ImageService
import junktion.v1.api.InfrastructureException
import junktion.v1.core.Image
import org.apache.commons.io.FilenameUtils
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

    override fun upload(multipartFile: MultipartFile, tag: String) {
        val name = makeFileName(multipartFile, tag)
        val image = Image(name, multipartFile.bytes)
        imageService.upload(image)
    }

    private fun makeFileName(multipartFile: MultipartFile, tag: String): String {
        val originalFileName = multipartFile.originalFilename ?: throw IllegalStateException()
        val extension = FilenameUtils.getExtension(originalFileName)
        return if (extension.isNotEmpty()) "$tag.$extension" else tag
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException::class)
    fun handleException(): String {
        return "バリデーションエラーです"
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InfrastructureException::class)
    fun handleServerException(): String {
        return "DB処理でのエラーです"
    }
}