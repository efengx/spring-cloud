package com.yangst.cloud.demo.controller;

import com.yangst.cloud.demo.api.entity.User;
import com.yangst.cloud.demo.api.service.HelloRefactorService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRefactorController implements HelloRefactorService {

	@Override
	public String hello(@RequestParam("name") String name) {
		return "Hello"+name;
	}
	@Override
	public User hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age) {
		return new User(name,age);
	}
	@Override
	public String hello(@RequestBody User user) {
		return "Hello"+user.getName()+","+user.getAge();
	}

}
