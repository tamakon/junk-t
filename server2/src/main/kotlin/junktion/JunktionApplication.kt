package junktion

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
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
 */
@Configuration
@EnableSwagger2
@Profile("dev")
class SwaggerConfig

@RequestMapping("/")
@Controller
class ExampleController {
	@GetMapping("example")
	fun example() = "example"
	@GetMapping("upload_image_example")
	fun uploadImageExample() = "upload_image_example"
}

@Api(description = "ああああ")
@RestController
@RequestMapping("api/v1")
class WawawaController {

	@GetMapping("aaa")
	@ApiOperation(value = "あああああああああああああああああ")
	fun hoho(): String {
		return "aaa"
	}
}