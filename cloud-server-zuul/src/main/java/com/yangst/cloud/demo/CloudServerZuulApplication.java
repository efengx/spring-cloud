package com.yangst.cloud.demo;

import com.yangst.cloud.demo.filter.AccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;
import org.springframework.context.annotation.Bean;

//开启zuul的Api网关服务功能
@EnableZuulServer
@EnableDiscoveryClient
@SpringBootApplication
public class CloudServerZuulApplication {

	/**
	 * 创建实体类 交于容器 用于启动
	 * @return
	 */
	@Bean
	public AccessFilter accessFilter() {
		return new AccessFilter();
	}

	public static void main(String[] args) {
		SpringApplication.run(CloudServerZuulApplication.class, args);
	}
}
