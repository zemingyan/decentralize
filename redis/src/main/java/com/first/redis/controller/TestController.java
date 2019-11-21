package com.first.redis.controller;

import com.first.redis.ope.RedisMessageSubscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    @Qualifier("messageListener1")
    private RedisMessageSubscriber subscriber1;

    @Autowired
    private PubMsg pubMsg;

    @GetMapping(value = "/a")
    public String index(){
        return "index";
    }

    @GetMapping(value = "/get")
    public String get(){
        List<String> list = subscriber1.getMessageList();
        new Thread(new Mid()).start();
        logger.info("  获得数据");

        for (String msg : list){
            System.out.println(msg);
        }
        logger.info("是否会阻塞");
        return "success";
    }
}
