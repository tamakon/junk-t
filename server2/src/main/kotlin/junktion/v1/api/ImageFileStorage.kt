package junktion.v1.api

import java.io.File

interface ImageFileStorage {
	fun save(file: File, tag: String)
}