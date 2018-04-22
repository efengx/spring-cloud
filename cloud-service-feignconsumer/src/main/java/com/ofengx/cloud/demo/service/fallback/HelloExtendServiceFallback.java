package com.yangst.cloud.demo.service.fallback;

import com.yangst.cloud.demo.entity.User;
import com.yangst.cloud.demo.service.HelloExtendService;
import feign.hystrix.FallbackFactory;

/**
 * 使用FallbackFactory
 * Created by YST on 2018/4/9.
 */
public class HelloExtendServiceFallback implements FallbackFactory<HelloExtendService>{
    @Override
    public HelloExtendService create(Throwable throwable) {
        return new HelloExtendService() {
            @Override
            public String hello() {
                return null;
            }

            @Override
            public String hello(String name) {
                return null;
            }

            @Override
            public User hello(String name, Integer age) {
                return null;
            }

            @Override
            public String hello(User user) {
                return null;
            }
        };
    }
}
