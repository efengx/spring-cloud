package com.ofengx.cloudserversso.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import java.security.Principal

@RestController
class Dashboard {

    @RequestMapping('/user')
    Principal user(Principal user) {
        return user
    }


}
