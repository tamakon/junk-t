package com.junktion

import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
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

@Configuration
@EnableResourceServer
class ResourceServerConfig: ResourceServerConfigurerAdapter() {

	/**
	 * apiはdefaultスコープを保持している場合のみアクセス可能。
	 */
	override fun configure(http: HttpSecurity) {
		http.requestMatchers().antMatchers("/api/**").and()
				.authorizeRequests().anyRequest().access("#oauth2.hasScope('default')")
	}
}