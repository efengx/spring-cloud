package com.ofengx.cloud.demo.service

import com.ofengx.cloud.demo.entity.User
import com.ofengx.cloud.demo.service.fallback.HelloExtendServiceFallback
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.*


/**
 * 服务降级失败 需要验证
 * 自动会服务失败重试 无法关闭 需要验证
 * @author YST
 *
 */
@FeignClient(name="hello-service",fallback=HelloExtendServiceFallback.class)
interface HelloExtendService {
    @RequestMapping("/hello")
    String hello()

    @RequestMapping(value = "/hello1",method = RequestMethod.GET)
    String hello(@RequestParam("name") String name)

    @RequestMapping(value = "/hello2",method = RequestMethod.GET)
    User hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age)

    @RequestMapping(value = "/hello3",method = RequestMethod.POST)
    String hello(@RequestBody User user)

}
