package com.first.serveritem;

import com.first.configdeal.config.TopicConfig;
import com.first.configdeal.util.NodeCache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



import java.util.Map;
import java.util.Set;


@SpringBootApplication
public class ServeritemApplication implements CommandLineRunner {
    private Logger logger = LoggerFactory.getLogger(ServeritemApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ServeritemApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        new TopicConfig().readConfigFile();
        for (Map.Entry<String, Set<String>> entry: NodeCache.nextServer.entrySet()){
            logger.info(entry.getKey() + "  " + entry.getValue().toString());
        }
    }
}
