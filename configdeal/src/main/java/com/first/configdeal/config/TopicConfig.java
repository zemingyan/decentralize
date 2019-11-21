package com.first.configdeal.config;

import com.first.configdeal.topic.TopicOpear;
import com.first.configdeal.util.NodeCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;

@RestController

public class TopicConfig {
    private static Logger logger = LoggerFactory.getLogger(TopicConfig.class);

    @Autowired
    private Environment environment;

    private Properties properties;

    private List<String> before = new ArrayList<>();

    private List<String> after = new ArrayList<>();


    //@GetMapping(value = "/")
    public String readConfigFile(){
        try {
            properties = PropertiesLoaderUtils.loadAllProperties("topic.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] allServer = properties.getProperty("allServer").split(",");
        Collections.addAll(before, allServer);
        Collections.addAll(after, allServer);

        for (String serverName : allServer){
            String context = properties.getProperty(serverName + ".next");
            if (context == null || context.equals("")) { // 有一个为空
                continue;
            }
            String[] next = context.split(",");
            Set<String> servers = new HashSet<>(next.length);
            Collections.addAll(servers, next);
            NodeCache.nextServer.put(serverName, servers);
            logger.info(serverName + "  " + servers.toString());


            for (String name : servers){ // 生成topic
                StringBuffer stringBuffer = new StringBuffer(serverName);
                stringBuffer.append("2").append(name);
                String topicName =  stringBuffer.toString();
                logger.info(topicName);
                TopicOpear.createTopic(topicName, "msg");
            }

        }
        System.out.println("=================");

        for (String serverName : allServer){
            String context = properties.getProperty(serverName + ".last");
            if (context == null || context.equals("")){
                continue;
            }
            String[] last = context.split(",");
            Set<String> servers = new HashSet<>(last.length);
            Collections.addAll(servers, last);
            NodeCache.lastServer.put(serverName, servers);
        }

        return "suc";
    }
    @GetMapping("/index")
    public String index(){
        System.out.println(environment.getProperty("first.num") + "  " + environment.getProperty("topic.num"));
        return environment.getProperty("topic.num");
    }
}
