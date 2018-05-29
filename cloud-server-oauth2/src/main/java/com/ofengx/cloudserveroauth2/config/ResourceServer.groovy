package com.ofengx.cloudserveroauth2.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter

@Configuration
@EnableResourceServer
class ResourceServer extends ResourceServerConfigurerAdapter {

    @Override
    void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/me")
                .authorizeRequests().anyRequest().authenticated()
    }
}