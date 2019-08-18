package com.junktion

import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer

@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfig(
		private val authenticationManager: AuthenticationManager
): AuthorizationServerConfigurerAdapter() {

	/**
	 * クライアント情報
	 */
	override fun configure(clients: ClientDetailsServiceConfigurer) {
		clients.inMemory()
				.withClient("fooClientId")
				.secret("{noop}secret")
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
 * リソースの認可設定は[メソッド単位][https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#jc-method]で定義しています。
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
class ResourceServerConfig: ResourceServerConfigurerAdapter()