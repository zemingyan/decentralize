package com.first.configdeal.topic;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class TopicOpear {
    private static Properties config = new Properties();
    public static void main(String[] args) {
        //createTopic("fff", "ff");
        Set<String> topics = selectTopic();
       // deleteTopic(topics);
    }
   public static void createTopic(String topic, String msg){
        if (msg == null || msg.equals("")) {
            msg = "msg";
        }

        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        AdminClient admin = AdminClient.create(config);

        Map<String, String> configs = new HashMap<>();
        int partitions = 1;
        short replication = 1;

        Set<String> topics = selectTopic();
        if (topics.contains(topic)){ // 已经存在
            return;
        }

        CreateTopicsResult result = admin.createTopics(Arrays.asList(new NewTopic(topic, partitions, replication).configs(configs)));

       KafkaFuture kafkaFuture = result.all();
       try {
           kafkaFuture.get();
           System.out.println("执行成功");
       } catch (InterruptedException e) {
           e.printStackTrace();
       } catch (ExecutionException e) {
           e.printStackTrace();
       }


    }

    public static Set<String> selectTopic(){

        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        AdminClient admin = AdminClient.create(config);

        Map<String, String> configs = new HashMap<>();
        int partitions = 1;
        short replication = 1;

        ListTopicsResult result = admin.listTopics();
        KafkaFuture<Set<String>> future = result.names();
        Set<String> topics = new HashSet<>();
        try {
            topics = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        for (String name : topics){
            System.out.println(name);
        }
        return topics;
        // List<NewTopic> lists = Arrays.asList(new NewTopic(topic, partitions, replication).configs(configs));
    }

    public static void deleteTopic(Set<String> topics){
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        AdminClient admin = AdminClient.create(config);


        DeleteTopicsResult deleteTopicsResult = admin.deleteTopics(topics);
        KafkaFuture<Void> future = deleteTopicsResult.all();
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
