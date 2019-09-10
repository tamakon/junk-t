package junktion.v1.mybatis

import java.time.LocalDateTime

data class ImageRecord(
		val name: String,
		val createdAt: LocalDateTime,
		val updatedAt: LocalDateTime
)