package com.first.redis;

import com.first.redis.ope.MessagePublisher;
import com.first.redis.ope.RedisMessageSubscriber;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.UUID;



@SpringBootTest
class RedisApplicationTests {
    private static Logger logger = LoggerFactory.getLogger(RedisApplicationTests.class);

    @Test
    void contextLoads() {
    }
    @Autowired
    @Qualifier("redisPublisherForTopic1")
    private MessagePublisher redisPublisher1;

    @Autowired
    @Qualifier("redisPublisherForTopic2")
    private MessagePublisher redisPublisher2;

    @Autowired
    @Qualifier("messageListener1")
    private RedisMessageSubscriber subscriber1;

    @Autowired
    @Qualifier("messageListener2")
    private RedisMessageSubscriber subscriber2;


    @Test
    public void test1() {

        // 订阅消息
        List<String> messageList2 = subscriber2.getMessageList();
        for (int i = 0; i < messageList2.size(); i++) {
            System.out.println(messageList2.get(i));

        }System.out.println("接受消息1的时间　　"  + System.currentTimeMillis()%1000);

        // 循环发布10次消息, 主要方法 redisTemplate.convertAndSend
        for (int i = 0; i < 10; i++) {
            String message = "Topic2 Message : " + UUID.randomUUID();
            redisPublisher2.publish(message);
        }

        // 循环发布10次消息, 主要方法 redisTemplate.convertAndSend
        for (int i = 0; i < 10; i++) {
            String message = "Topic1 Message : " + UUID.randomUUID();
            redisPublisher1.publish(message);
        }


        // 订阅消息
        List<String> messageList1 = subscriber1.getMessageList();
        for (int i = 0; i < messageList1.size(); i++) {

            System.out.println(messageList1.get(i) );
        }System.out.println("接受消息1的时间2　　"  + System.currentTimeMillis()%1000);


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // foreach 循环会报错： ConcurrentModificationException
        // 解决： https://www.cnblogs.com/dolphin0520/p/3933551.html
        /*for (String msg : messageList) {
            log.info(msg);
        }*/

    }


}
