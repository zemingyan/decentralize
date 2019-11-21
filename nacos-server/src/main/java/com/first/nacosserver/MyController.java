package com.first.nacosserver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @GetMapping(value = "/index")
    public String index(){
        return "index";
    }
}
