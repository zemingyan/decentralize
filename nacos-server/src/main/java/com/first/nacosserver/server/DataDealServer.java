package com.first.nacosserver.server;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@EnableDiscoveryClient
@RestController
public class DataDealServer {

    @GetMapping(value = "/fir")
    public String deal(@RequestParam(value = "name")String data){
        return "fir this " + data;
    }

    @GetMapping(value = "/test")
    public String test(@RequestParam(value = "name") String name){
        return "server deal " + name;
    }
}
