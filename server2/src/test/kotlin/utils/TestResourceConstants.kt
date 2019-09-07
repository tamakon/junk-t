package utils

import org.springframework.core.io.ClassPathResource
import java.io.File

val TEST_IMAGE_FILE = File(ClassPathResource("images/test_image_file.png").uri)