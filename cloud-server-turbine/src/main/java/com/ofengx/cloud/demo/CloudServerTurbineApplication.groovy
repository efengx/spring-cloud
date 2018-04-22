package com.ofengx.cloud.demo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard
import org.springframework.cloud.netflix.turbine.EnableTurbine

@EnableHystrixDashboard
@EnableTurbine
@SpringBootApplication
class CloudServerTurbineApplication {

    static void main(String[] args) {
        SpringApplication.run(CloudServerTurbineApplication.class, args)
    }
}
