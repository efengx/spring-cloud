package com.ofengx.cloud.demo.service

import com.ofengx.cloud.demo.entity.User
import com.ofengx.cloud.demo.service.fallback.HelloServiceFallback
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam


/**
 * 服务降级失败 需要验证
 * 自动会服务失败重试 无法关闭 需要验证
 * @author YST
 *
 */
@FeignClient(name="hello-service",fallback=HelloServiceFallback.class)
interface HelloService {
    @RequestMapping("/hello")
    String hello();

    @RequestMapping(value = "/hello1",method = RequestMethod.GET)
    String hello(@RequestParam("name") String name);

    @RequestMapping(value = "/hello2",method = RequestMethod.GET)
    User hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age);

    @RequestMapping(value = "/hello3",method = RequestMethod.POST)
    String hello(@RequestBody User user);

}
