package com.yangst.cloud.demo.service;

import com.yangst.cloud.demo.entity.User;
import com.yangst.cloud.demo.service.fallback.HelloExtendServiceFallback;
import com.yangst.cloud.demo.service.fallback.HelloServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * 服务降级失败 需要验证
 * 自动会服务失败重试 无法关闭 需要验证
 * @author YST
 *
 */
@FeignClient(name="hello-service",fallback=HelloExtendServiceFallback.class)
public interface HelloExtendService {
	@RequestMapping("/hello")
	String hello();

	@RequestMapping(value = "/hello1",method = RequestMethod.GET)
	public String hello(@RequestParam("name") String name);
	
	@RequestMapping(value = "/hello2",method = RequestMethod.GET)
	public User hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age);
	
	@RequestMapping(value = "/hello3",method = RequestMethod.POST)
	public String hello(@RequestBody User user);

}
