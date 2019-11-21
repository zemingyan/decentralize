package com.first.consumer.controller;

import com.first.consumer.feign.ProviderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyFeignClient {
    @Autowired
    private ProviderClient providerClient;
/*

    @Autowired
    private DataDeal dataDeal;
*/

    @GetMapping(value = "/client")
    public String client(@RequestParam(value = "name",defaultValue = "name") String sss){
        System.out.println(sss);
        return providerClient.client(sss);
    }

    @PostMapping(value = "/deal")
    public String del(@RequestParam(value = "data")String data){
        System.out.println("deal before");
        return providerClient.deal(data);
    }

    @PostMapping(value = "post")
    public String po(){
        return "post";
    }

    @GetMapping(value = "/")
    public String index(){
        return "index";
    }

    @GetMapping(value = "/fir")
    public String fir(@RequestParam(value = "name")String name){
        return providerClient.fir(name);
    }
}