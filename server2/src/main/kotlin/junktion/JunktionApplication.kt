package junktion

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication
@MapperScan(basePackages = ["junktion.v1"], annotationClass = Repository::class)
class JunktionApplication

fun main(args: Array<String>) {
	runApplication<JunktionApplication>(*args)
}

/**
 * クライアント用のAPIインターフェースを作成するために [API仕様Jsonファイルパス(/v2/api-docs)](Swagger2Controller.DEFAULT_URL) を公開します。
 * 前述の内容のみが目的であるため、開発環境でしか公開しません。
 * テスト環境での有効化はテストカバレッジ確保のためです。
 */
@Configuration
@EnableSwagger2
@Profile("dev", "test")
class SwaggerConfig

@RequestMapping("/")
@Controller
class ExampleController {
	@GetMapping("upload_image_example")
	fun uploadImageExample() = "upload_image_example"
}
