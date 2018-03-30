package com.yangst.cloud.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient//注册服务
@SpringBootApplication
public class CloudServiceFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudServiceFeignApplication.class, args);
	}
}
