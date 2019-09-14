package junktion.v1.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

fun createImage(name: String, content: ByteArray) = Image(name, content)

internal class ImageTest {

    @Test
    @DisplayName("各フィールドの取得処理動作確認")
    fun fields() {
        val image = createImage("image", ByteArray(0))
        assertThat(image.name).isEqualTo("image")
        assertThat(image.content).isEqualTo(ByteArray(0))
    }

    @Test
    @DisplayName("同一性確認が正しく動作していること")
    fun equality() {
        val criterion = createImage("image", ByteArray(0))
        val sameFields = createImage("image", ByteArray(0))
        val theOtherName = createImage("different_name", ByteArray(0))
        val theOtherContent = createImage("image", ByteArray(1) { 1 })
        val theOtherInstance = "not a image" as Any?
        val nullObject = null as Any?

        assertThat(criterion == criterion).isTrue()
        assertThat(criterion == sameFields).isTrue()
        assertThat(criterion == theOtherName).isFalse()
        assertThat(criterion == theOtherContent).isFalse()
        assertThat(criterion == theOtherInstance).isFalse()
        assertThat(criterion == nullObject).isFalse()
    }

    @Test
    @DisplayName("ハッシュコード生成の既存動作保証")
    fun hashCodeValue() {
        val image = createImage("image", ByteArray(0))
        assertThat(image.hashCode()).isEqualTo(-1185250810)
    }
}