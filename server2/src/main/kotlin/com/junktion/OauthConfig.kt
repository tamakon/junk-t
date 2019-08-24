package com.junktion

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler
import org.springframework.stereotype.Component


@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfig(
		private val authenticationManager: AuthenticationManager,
		private val oauth2ClientConfig: Oauth2ClientConfig
): AuthorizationServerConfigurerAdapter() {

	/**
	 * クライアント情報
	 */
	override fun configure(clients: ClientDetailsServiceConfigurer) {
		clients.inMemory()
				.withClient(oauth2ClientConfig.clientId)
				.secret("{noop}${oauth2ClientConfig.clientSecret}")
				.authorizedGrantTypes("password")
				.scopes("default")
	}

	/**
	 * 公開されている[AuthenticationManager]Beanを使用する。
	 */
	override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
		endpoints.authenticationManager(authenticationManager)
	}
}

/**
 * リソースの認可制限は[メソッド単位][https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#jc-method]で定義しています。
 */
@Configuration
@EnableResourceServer
class ResourceServerConfig: ResourceServerConfigurerAdapter() {
	override fun configure(http: HttpSecurity) {
		http.authorizeRequests().antMatchers("/api/**").permitAll()
	}
}

/**
 * メソッド単位のセキュリティではoauth2用の表記を使用します。
 */
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
class MethodSecurityConfig: GlobalMethodSecurityConfiguration() {
	override fun createExpressionHandler(): MethodSecurityExpressionHandler {
		return OAuth2MethodSecurityExpressionHandler()
	}
}

@Component
@ConfigurationProperties(prefix = "security.oauth2.client")
class Oauth2ClientConfig {
	lateinit var clientId: String
	lateinit var clientSecret: String
}