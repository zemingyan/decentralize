package com.first.nacosserver.server;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableDiscoveryClient
public class ProviderServer {
    @RequestMapping(value = "/client", method = RequestMethod.GET)
    public String ed(@RequestParam(value = "name") String ff){ //feign调用端最好使用这种参数形式　　/{int} /{string}无法识别
        return "ServerProvider  " + ff;
    }

    @RequestMapping(value = "/ss/{string}", method = RequestMethod.GET)  //　　/path/{int} 可以作为模板方式的服务端实现
    public String echo(@PathVariable String string) {

        return "Hello Nacos Discovery " + string;
    }
    @GetMapping(value = "/ssss/{string}")
    public String echos(@PathVariable String string) {

        return "Hello Nacos Discoveryssss " + string;
    }
    @PostMapping(value = "/deal")
    public String deal(@RequestParam(value = "data")String data){
        return "deal this " + data;
    }
}