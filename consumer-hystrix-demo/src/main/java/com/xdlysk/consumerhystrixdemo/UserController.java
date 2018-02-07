package com.xdlysk.consumerhystrixdemo;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {


    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "findByNameFallback")
    @GetMapping("/user/{name}")
    public UserInfo get(@PathVariable String name){
        return this.restTemplate.getForObject("http://localhost:9000/user/"+name,UserInfo.class);
    }

    //fallback 回调
    public UserInfo findByNameFallback(String name){
        UserInfo userInfo = new UserInfo();
        userInfo.setName("默认用户");
        userInfo.setBirthday("0000-00-00");
        userInfo.setMobile("00000000000");
        return userInfo;
    }
}
