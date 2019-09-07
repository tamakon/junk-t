package junktion.v1.api

import java.io.File

interface ImageService {
	fun upload(): String
	fun upload(file: File, tag: String)
}