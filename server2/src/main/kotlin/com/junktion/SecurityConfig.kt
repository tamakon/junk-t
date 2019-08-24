package com.junktion

import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.stereotype.Component
import springfox.documentation.swagger2.web.Swagger2Controller

@Configuration
@EnableWebSecurity
class SecurityConfig(
		private val junktionAdminConfig: JunktionAdminConfig
) : WebSecurityConfigurerAdapter() {

	/** 使用例ページのパス */
	private val exampleScreenPath = AntPathRequestMatcher("/example")
	/** ルートパス */
	private val rootPath = AntPathRequestMatcher("/")
	/** 認可サーバのパス */
	private val authPath = AntPathRequestMatcher("/oauth/token")
	/** リソースサーバ(API)のパス */
	private val resourcePath = AntPathRequestMatcher("/api/**")

	override fun configure(http: HttpSecurity) {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		http.formLogin().disable()
		http.httpBasic().disable()
		http.authorizeRequests().requestMatchers(rootPath, exampleScreenPath).permitAll()
		http.authorizeRequests().requestMatchers(authPath).permitAll()
		http.authorizeRequests().requestMatchers(resourcePath).permitAll()
	}

	/**
	 * ユーザ情報
	 */
	override fun configure(auth: AuthenticationManagerBuilder) {
		val inMemoryAuthentication = auth.inMemoryAuthentication()
		inMemoryAuthentication.withUser(junktionAdminConfig.userId)
				.password("{noop}${junktionAdminConfig.password}")
				.roles("USER")
	}

	/**
	 * 以下のパスを認証対象外とします。
	 *
	 * - [API仕様Jsonファイルパス(/v2/api-docs)](Swagger2Controller.DEFAULT_URL)
	 * 	（swagger-codegenが認証なしでアクセスできるようにするため）
	 * -  エラーページ
	 *
	 * 参照: `server/bin/swagger-codegen-client-interface.sh`
	 */
	override fun configure(web: WebSecurity) {
		web.ignoring()
				.antMatchers(Swagger2Controller.DEFAULT_URL)
				.antMatchers("/error")
	}

	/**
	 * [AuthorizationServerConfig]で使用するため公開します。
	 */
	@Bean
	override fun authenticationManagerBean(): AuthenticationManager {
		return super.authenticationManagerBean()
	}

	/**
	 * [UserDetailsServiceAutoConfiguration]が必要のないデフォルトのパスワード生成を
	 * [ResourceServerConfig]の方のサーバ設定で行わないようにするためにBeanとしてexposeしています。
	 */
	@Bean
	override fun userDetailsServiceBean(): UserDetailsService {
		return super.userDetailsServiceBean()
	}
}

@Component
@ConfigurationProperties(prefix = "junk-t.admin")
class JunktionAdminConfig {
	lateinit var userId: String
	lateinit var password: String
}