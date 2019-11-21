package com.first.redis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FIrstController {

    @GetMapping("/")
    public String aa(){return "你是傻子";}

}
