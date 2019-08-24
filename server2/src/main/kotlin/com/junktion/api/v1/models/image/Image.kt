package com.junktion.api.v1.models.image

import java.time.LocalDateTime

data class Image(
		val tag: String,
		val createdAt: LocalDateTime,
		val updatedAt: LocalDateTime
)