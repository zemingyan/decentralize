package com.first.consumer.controller;

import com.first.consumer.feign.DataDeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RefreshScope
@RestController
public class TestController {

    @Autowired
    private DataDeal dataDeal;

    @GetMapping(value = "/test")
    public String name(@RequestParam(value = "v")String v){
        return dataDeal.te(v);
    }



    @Value("${first.name}")
    private String serverName;



    @Value("${name}")
    private String name;

    @GetMapping("/server")
    public String config(){
        return   name + "  " + serverName;
    }
}

