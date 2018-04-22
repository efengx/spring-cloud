package com.yangst.cloud.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

//启动一个服务注册中心提供给其他应用
@EnableEurekaServer
@SpringBootApplication
public class CloudServerEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudServerEurekaApplication.class, args);
	}
}
