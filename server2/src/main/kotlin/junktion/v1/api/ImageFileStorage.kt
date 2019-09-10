package junktion.v1.api

import junktion.v1.core.Image

interface ImageFileStorage {
	fun save(image: Image)
}