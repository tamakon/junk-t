package com.junktion.api.v1.models.image

import java.io.File

interface ImageFileStorage {
	fun save(file: File, tag: String)
}