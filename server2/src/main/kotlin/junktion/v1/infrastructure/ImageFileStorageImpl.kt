package junktion.v1.infrastructure

import junktion.v1.api.ImageFileStorage
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Repository
import org.springframework.util.FileCopyUtils
import java.io.File

@Repository
class ImageFileStorageImpl: ImageFileStorage {
	override fun save(file: File, tag: String) {
		val destinationUri = ClassPathResource("images/$tag").uri
		FileCopyUtils.copy(file, File(destinationUri))
	}
}