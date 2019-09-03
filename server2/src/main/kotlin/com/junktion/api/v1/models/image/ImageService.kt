package com.junktion.api.v1.models.image

import java.io.File

interface ImageService {
	fun upload(): String
	fun upload(file: File, tag: String)
}