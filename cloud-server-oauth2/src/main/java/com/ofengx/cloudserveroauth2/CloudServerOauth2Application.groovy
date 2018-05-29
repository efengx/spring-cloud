package com.ofengx.cloudserveroauth2

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.core.annotation.Order
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer

@SpringBootApplication
@EnableAuthorizationServer
@Order(200)
class CloudServerOauth2Application {

    static void main(String[] args) {
        SpringApplication.run(CloudServerOauth2Application.class, args)
    }

}
