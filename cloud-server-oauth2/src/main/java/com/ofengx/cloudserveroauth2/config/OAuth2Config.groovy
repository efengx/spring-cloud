package com.ofengx.cloudserveroauth2.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter

@Configuration
@EnableAuthorizationServer
class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    @Bean
    JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter()
        converter.setSigningKey('ofengx')
        return converter
    }

    @Override
    void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
        .withClient('client')
        .authorizedGrantTypes('password')
        .secret('{noop}secret')
        .scopes('all')
    }


//    token generating method
    @Override
    void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore()
                .accessTokenConverter(accessTokenConverter())
                .authenticationManager()
    }



}
