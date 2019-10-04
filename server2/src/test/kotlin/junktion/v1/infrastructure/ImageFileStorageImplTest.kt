package junktion.v1.infrastructure

import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import junktion.v1.api.InfrastructureException
import junktion.v1.core.Image
import org.apache.tomcat.util.http.fileupload.FileUtils
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.core.io.ClassPathResource
import org.springframework.util.FileCopyUtils
import java.io.File
import java.io.IOException

internal class ImageFileStorageImplTest {

    private lateinit var imageFileStorage: ImageFileStorageImpl
    private lateinit var imagesDirectoryProvider: LocalImagesDirectoryProvider

    @BeforeEach
    fun setup() {
        imagesDirectoryProvider = LocalImagesDirectoryProvider()
        imageFileStorage = ImageFileStorageImpl(imagesDirectoryProvider)
    }

    @AfterEach
    fun tearDown() {
        val imagesDirectory = imagesDirectoryProvider.provide()
        FileUtils.cleanDirectory(imagesDirectory)
    }

    @Test
    @DisplayName("対象のディレクトリにファイルが保存されること")
    fun save() {
        val image = Image("test.png", ByteArray(0))
        imageFileStorage.save(image)
        assertThat(File(ClassPathResource("static/images/").uri).resolve("test.png").exists()).isTrue()
    }

    @Test
    @DisplayName("IO例外が発生した場合にインフラ関連例外にラップされること")
    fun saveException() {
        mockkStatic(FileCopyUtils::class)
        every { FileCopyUtils.copy(any<ByteArray>(), any<File>()) } throws IOException()
        val image = Image("test.png", ByteArray(0))
        assertThatThrownBy { imageFileStorage.save(image) }
                .isInstanceOf(InfrastructureException::class.java)
        assertThat(File(ClassPathResource("static/images/").uri).resolve("test.png").exists()).isFalse()
        unmockkStatic(FileCopyUtils::class)
    }
}