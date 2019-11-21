package com.first.nacosconfig;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class FirstController {

    @Value("${name}")
    private String name;


    @Value("${first.name}")
    private String serverName;


    @GetMapping("/")
    public boolean index(){
        return true;
    }



    @GetMapping("/name")
    public String name(){
        return name;
    }
    @GetMapping("/server")
    public String config(){
        return   "  " + serverName;
    }

}