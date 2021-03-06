package junktion.v1.core

data class Image(
		val name: String,
		val content: ByteArray
) {
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as Image

		if (name != other.name) return false
		if (!content.contentEquals(other.content)) return false

		return true
	}

	override fun hashCode(): Int {
		var result = name.hashCode()
		result = 31 * result + content.contentHashCode()
		return result
	}
}