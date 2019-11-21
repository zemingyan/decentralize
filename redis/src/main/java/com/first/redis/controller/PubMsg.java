package com.first.redis.controller;

import com.first.redis.ope.MessagePublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RunAs;
import java.util.UUID;

@RestController
public class PubMsg   {
    private static Logger logger = LoggerFactory.getLogger(PubMsg.class);
    @Autowired
    @Qualifier("redisPublisherForTopic1")
    private MessagePublisher redisPublisher1;

    @GetMapping(value = "/t")
    public String pub() {

        for (int i = 0; i < 10; i++) {
            String message = "Topic1 Message : " + UUID.randomUUID();
            redisPublisher1.publish(message);
        }
       try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("pub  end");

        return "pub_success";
    }


}
