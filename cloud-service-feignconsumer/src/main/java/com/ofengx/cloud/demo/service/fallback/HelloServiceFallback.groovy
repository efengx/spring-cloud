package com.ofengx.cloud.demo.service.fallback

import com.ofengx.cloud.demo.entity.User

import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam


@Component
class HelloServiceFallback implements HelloService {

    @Override
    String hello() {
        // TODO Auto-generated method stub
        return "error"
    }

    @Override
    String hello(@RequestParam("name") String name) {
        // TODO Auto-generated method stub
        return "error"
    }

    @Override
    User hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age) {
        // TODO Auto-generated method stub
        return new User("none",0)
    }

    @Override
    String hello(@RequestBody User user) {
        // TODO Auto-generated method stub
        return "error"
    }

}
