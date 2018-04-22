package com.ofengx.cloud.demo

import de.codecentric.boot.admin.config.EnableAdminServer
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient


@EnableAdminServer
@EnableEurekaClient
@SpringBootApplication
class CloudServerAdminApplication {

    static void main(String[] args) {
        SpringApplication.run(CloudServerAdminApplication.class, args)
    }
}
