package junktion.v1.infrastructure

import junktion.v1.api.ImageFileStorage
import org.apache.tomcat.util.http.fileupload.FileUtils
import org.springframework.boot.autoconfigure.web.ResourceProperties
import org.springframework.context.annotation.Profile
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import org.springframework.util.FileCopyUtils
import java.io.File


@Repository
class ImageFileStorageImpl(
		private val imagesDirectoryProvider: ImagesDirectoryProvider
): ImageFileStorage {

	override fun save(sourceFile: File, tag: String) {
		val imagesDirectory = imagesDirectoryProvider.provide()
		val destinationFile = imagesDirectory.resolve("$tag.${sourceFile.extension}")
		FileCopyUtils.copy(sourceFile, destinationFile)
	}
}

interface ImagesDirectoryProvider {
	fun provide(): File
}

@Component
@Profile("dev", "test")
class LocalImagesDirectoryProvider: ImagesDirectoryProvider {

	/**
	 * Springで静的リソースとして自動認識されるクラスパスを使用しています。
	 * @see  ResourceProperties
	 */
	private val staticContentPath = "static"

	private val directory: File = run {
		val resource = ClassPathResource("")
		val resourceDirectory = File(resource.uri)
		val imagesDirectory = resourceDirectory.resolve("$staticContentPath/images")
		FileUtils.forceMkdir(imagesDirectory)
		imagesDirectory
	}

	override fun provide() = directory
}