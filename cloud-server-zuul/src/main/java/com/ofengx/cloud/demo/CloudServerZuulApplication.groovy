package com.ofengx.cloud.demo

import com.ofengx.cloud.demo.filter.AccessFilter
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.zuul.EnableZuulServer
import org.springframework.context.annotation.Bean


@EnableZuulServer
@EnableDiscoveryClient
@SpringBootApplication
class CloudServerZuulApplication {

    /**
     * @return
     */
    @Bean
    AccessFilter accessFilter() {
        return new AccessFilter()
    }

    static void main(String[] args) {
        SpringApplication.run(CloudServerZuulApplication.class, args)
    }
}
