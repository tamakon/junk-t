package junktion.v1.api

import org.springframework.security.access.prepost.PreAuthorize
import java.io.File

interface ImageService {
	@PreAuthorize("#oauth2.hasScope('default')")
	fun upload(file: File, tag: String)
}