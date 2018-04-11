package com.yangst.cloud.demo;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableAdminServer
@EnableEurekaClient
@SpringBootApplication
public class CloudServerAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudServerAdminApplication.class, args);
	}
}
