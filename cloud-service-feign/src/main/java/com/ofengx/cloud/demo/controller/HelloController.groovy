package com.ofengx.cloud.demo.controller

import com.ofengx.cloud.demo.entity.User

import java.util.logging.Logger


import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
class HelloController {

    private final Logger log = Logger.getLogger(HelloController.class.getName())


//    @Autowired
//    private DiscoveryClient discoveryClient

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    String index() throws InterruptedException {
        // Wait for a few seconds for the processing thread
        int sleep =  new Random().nextInt(3000)
        log.info("sleeptime:"+sleep)
        Thread.sleep(sleep)
        return "hello World"
    }


    @RequestMapping(value = "/hello",method = RequestMethod.POST)
    List<String> toindex(List<String> ids) throws InterruptedException {
        List<String> list =new ArrayList<>()
        for(int i=0;i<ids.size();i++) {
            list.add("hello World"+i)
        }
        return list
    }

    @RequestMapping(value = "/hello1",method = RequestMethod.GET)
    String hello(@RequestParam String name) {
        return "Hello"+name
    }

    @RequestMapping(value = "/hello2",method = RequestMethod.GET)
    User hello(@RequestHeader String name, @RequestHeader Integer age) {
        return new User(name,age)
    }

    @RequestMapping(value = "/hello3",method = RequestMethod.POST)
    String hello(@RequestBody User user) {
        return "Hello"+user.getName()+","+user.getAge()
    }

}
