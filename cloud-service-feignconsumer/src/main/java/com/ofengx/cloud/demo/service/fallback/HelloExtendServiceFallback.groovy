package com.ofengx.cloud.demo.service.fallback

import com.ofengx.cloud.demo.entity.User

import feign.hystrix.FallbackFactory

/**
 * 使用FallbackFactory
 * Created by YST on 2018/4/9.
 */
class HelloExtendServiceFallback implements FallbackFactory<HelloExtendService>{
    @Override
    HelloExtendService create(Throwable throwable) {
        return new HelloExtendService() {
            @Override
            String hello() {
                return null
            }

            @Override
            String hello(String name) {
                return null
            }

            @Override
            User hello(String name, Integer age) {
                return null
            }

            @Override
            String hello(User user) {
                return null
            }
        };
    }
}
