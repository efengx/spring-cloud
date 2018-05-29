package com.ofengx.cloudserversso

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso

@SpringBootApplication
class CloudServerSsoApplication {

    static void main(String[] args) {
        SpringApplication.run(CloudServerSsoApplication.class, args)
    }
}
