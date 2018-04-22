package com.ofengx.cloud.demo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard

@EnableHystrixDashboard
@SpringBootApplication
class CloudServerHystrixDashboardApplication {

    static void main(String[] args) {
        SpringApplication.run(CloudServerHystrixDashboardApplication.class, args)
    }
}
