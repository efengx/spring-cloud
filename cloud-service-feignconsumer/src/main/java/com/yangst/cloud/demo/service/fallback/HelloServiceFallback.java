package com.yangst.cloud.demo.service.fallback;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.yangst.cloud.demo.entity.User;
import com.yangst.cloud.demo.service.HelloService;

@Component
public class HelloServiceFallback implements HelloService {

	@Override
	public String hello() {
		// TODO Auto-generated method stub
		return "error";
	}

	@Override
	public String hello(@RequestParam("name") String name) {
		// TODO Auto-generated method stub
		return "error";
	}

	@Override
	public User hello(@RequestHeader("name") String name,@RequestHeader("age") Integer age) {
		// TODO Auto-generated method stub
		return new User("未知",0);
	}

	@Override
	public String hello(@RequestBody User user) {
		// TODO Auto-generated method stub
		return "error";
	}

}
