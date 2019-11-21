package com.first.consumer.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("nacos-provider") //nacos服务注册中的　　服务名
public interface ProviderClient {
    @GetMapping(value = "/client")  //服务提供方必须要对应的实现
    String client(@RequestParam(value = "name") String str);

    @PostMapping(value = "/deal")
    String deal(@RequestParam(value = "data")String data);

    @GetMapping(value = "/fir")
    String fir(@RequestParam(value = "name") String name);
}