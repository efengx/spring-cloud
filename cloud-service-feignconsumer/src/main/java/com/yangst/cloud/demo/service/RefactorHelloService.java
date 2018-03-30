package com.yangst.cloud.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import com.yangst.cloud.demo.api.HelloRefactorService;

@FeignClient("hello-service")
public interface RefactorHelloService extends HelloRefactorService {

}
