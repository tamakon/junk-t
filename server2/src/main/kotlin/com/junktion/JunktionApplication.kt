package com.junktion

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication
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

@Controller
@RequestMapping("/")
class HomeController {
	@GetMapping("example")
	fun example(): String {
		return "example"
	}
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