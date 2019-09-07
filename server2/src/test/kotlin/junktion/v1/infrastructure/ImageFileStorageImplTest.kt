package junktion.v1.infrastructure

import org.apache.tomcat.util.http.fileupload.FileUtils
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.core.io.ClassPathResource
import utils.TEST_IMAGE_FILE
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
        imageFileStorage.save(TEST_IMAGE_FILE, "tag")
        assertThat(File(ClassPathResource("images/tag.png").uri).exists()).isTrue()
    }
}