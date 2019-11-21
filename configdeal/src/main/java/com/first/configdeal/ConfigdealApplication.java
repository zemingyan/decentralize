package com.first.configdeal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class ConfigdealApplication {

    public static void main(String[] args) {
       ApplicationContext applicationContext =  SpringApplication.run(ConfigdealApplication.class, args);

        Binder binder = Binder.get(applicationContext.getEnvironment());

// 绑定List配置
        List<String> post = binder.bind("com.didispace.post", Bindable.listOf(String.class)).get();
        System.out.println(post);

    /*    List<PostInfo> posts = binder.bind("com.didispace.posts", Bindable.listOf(PostInfo.class)).get();
        System.out.println(posts);*/


    }

}
