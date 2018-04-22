package com.ofengx.cloud.demo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient


@EnableDiscoveryClient
@SpringBootApplication
class CloudServiceFeignApplication {

    static void main(String[] args) {
        SpringApplication.run(CloudServiceFeignApplication.class, args)
    }
}
