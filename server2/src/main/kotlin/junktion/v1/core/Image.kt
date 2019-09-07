package junktion.v1.core

import java.time.LocalDateTime

data class Image(
		val tag: String,
		val createdAt: LocalDateTime,
		val updatedAt: LocalDateTime
)