package com.first.redis.ope;

import lombok.*;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Redis Message Subscriber
 * <p>
 * RedisMessageSubscriber implements the Spring Data Redis-provided MessageListener interface
 *
 * @author ijiangtao
 * @create 2019-05-01 19:39
 **/
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Data
@Component
public class RedisMessageSubscriber implements MessageListener {


    private List<String> messageList;

    public RedisMessageSubscriber(ArrayList<String> objects) {
        this.messageList = objects;
    }

    /* public RedisMessageSubscriber(List<String> list){
         this.messageList = list;
     }
 */
    public void onMessage(Message message, byte[] pattern) {
        messageList.add("[pattern:" + new String(pattern) + ",message:" + message.toString() + "]");
    }

    public List<String> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<String> messageList) {
        this.messageList = messageList;
    }
}