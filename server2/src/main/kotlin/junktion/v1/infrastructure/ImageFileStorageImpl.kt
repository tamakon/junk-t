package junktion.v1.infrastructure

import junktion.v1.api.ImageFileStorage
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

	private val directory: File = run {
		val resource = ClassPathResource("")
		val resourceDirectory = File(resource.uri)
		val imagesDirectory = resourceDirectory.resolve("images")
		if (!imagesDirectory.exists()) {
			imagesDirectory.mkdir()
		}
		imagesDirectory
	}

	override fun provide() = directory
}