package com.first.consumer.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient("nacos-provider")
public interface DataDeal {

    @GetMapping(value = "/test")
    public String te(@RequestParam(value = "name")String name);
}

