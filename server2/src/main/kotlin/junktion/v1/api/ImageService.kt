package junktion.v1.api

import junktion.v1.core.Image
import org.springframework.security.access.prepost.PreAuthorize

interface ImageService {
	@PreAuthorize("#oauth2.hasScope('default')")
	fun upload(image: Image)
}