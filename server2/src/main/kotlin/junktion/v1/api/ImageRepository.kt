package junktion.v1.api

import junktion.v1.core.Image

interface ImageRepository {
	fun register(image: Image)
}