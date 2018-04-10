package com.yangst.cloud.demo.controller;

import com.yangst.cloud.demo.entity.User;
import com.yangst.cloud.demo.service.HelloService;
import com.yangst.cloud.demo.service.RefactorHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ConsumerController {
	@Autowired
	HelloService helloService;

	@Value("${test}")
	String test;

	@RequestMapping(value ="/getString",method=RequestMethod.GET)
	public String getString(){
		return test;
	}

	@RequestMapping(value ="/feign-consumer",method=RequestMethod.GET)
	public String helloConsumer(){
		return helloService.hello();
	}
	
	@RequestMapping(value ="/feign-consumer2",method=RequestMethod.GET)
	public String helloConsumer2(){
		StringBuffer sb =new StringBuffer();
		sb.append(helloService.hello()).append("\n");
		sb.append(helloService.hello("DADA")).append("\n");
		sb.append(helloService.hello("DADA", 30)).append("\n");
		sb.append(helloService.hello(new User("didi",60))).append("\n");
		return sb.toString();
	}
	
	/*@Autowired
	private RefactorHelloService refactorHelloService;


	@RequestMapping(value ="/feign-consumer3",method=RequestMethod.GET)
	public String helloConsumer3(){
		StringBuffer sb =new StringBuffer();
		sb.append(refactorHelloService.hello("DADA")).append("\n");
		sb.append(refactorHelloService.hello("DADA", 30)).append("\n");
		sb.append(refactorHelloService.hello(new com.yangst.cloud.demo.api.entity.User("didi",60))).append("\n");
		return sb.toString();
	}*/
}
