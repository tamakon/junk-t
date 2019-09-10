package junktion.v1.infrastructure

import junktion.v1.core.Image
import org.apache.tomcat.util.http.fileupload.FileUtils
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.core.io.ClassPathResource
import java.io.File

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
        assertThat(File(ClassPathResource("static/images/test.png").uri).exists()).isTrue()
    }
}