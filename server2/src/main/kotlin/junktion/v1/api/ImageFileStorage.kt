package junktion.v1.api

import java.io.File

interface ImageFileStorage {
	fun save(sourceFile: File, tag: String)
}