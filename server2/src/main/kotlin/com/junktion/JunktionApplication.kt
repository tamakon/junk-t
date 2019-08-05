package com.junktion

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import springfox.documentation.swagger2.annotations.EnableSwagger2
import springfox.documentation.swagger2.web.Swagger2Controller

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

@Configuration
class SecurityConfig : WebSecurityConfigurerAdapter() {

	/**
	 * swagger-codegenが認証なしで[API仕様Jsonファイルパス(/v2/api-docs)](Swagger2Controller.DEFAULT_URL)
	 * にアクセスできるようにするために、対象のパスをセキュリティ検証対象外とします。
	 *
	 * 参照: `server/bin/swagger-codegen-client-interface.sh`
	 */
	override fun configure(web: WebSecurity) {
		web.ignoring().antMatchers(Swagger2Controller.DEFAULT_URL)
	}
}