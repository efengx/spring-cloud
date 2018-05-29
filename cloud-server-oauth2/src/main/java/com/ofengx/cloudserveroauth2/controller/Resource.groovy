package com.ofengx.cloudserveroauth2.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import java.security.Principal

@RestController
class Resource {

    @RequestMapping(['/user', '/me' ])
    Map<String, String> user(Principal principal) {
        Map<String, String> map = new LinkedHashMap<>()
        map.put("name", principal.getName())
        return map
    }
}