package com.ofengx.cloud.demo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@EnableEurekaServer
@SpringBootApplication
class CloudServerEurekaApplication {

    static void main(String[] args) {
        SpringApplication.run(CloudServerEurekaApplication.class, args)
    }
}
