package com.xdlysk.consumerhystrixdemo;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserCacheController {
    @Autowired
    private RestTemplate restTemplate;

    @CacheResult //指定调用该请求时建立缓存
    @HystrixCommand(commandKey = "findByName" /*命令key，用于缓存移除*/,fallbackMethod = "findByNameFallback")
    @GetMapping("/usercache/{name}")
    @HystrixProperty(name = "execution.isolation.strategy",value = "THREAD")
    public UserInfo get(@PathVariable @CacheKey("name")/*缓存key*/ String name){
        return this.restTemplate.getForObject("http://localhost:9000/user/"+name,UserInfo.class);
    }

    @CacheRemove(commandKey = "findByName") //对指定commandKey命令移除缓存
    @HystrixCommand(fallbackMethod = "updateFallback")
    @PutMapping(value = "/usercache/{name}")
    public String put(@PathVariable("name") @CacheKey("name")/*缓存key*/ String name,@RequestBody UserInfo userInfo){
        return this.restTemplate.postForObject("http://localhost:9000/user/"+name,userInfo,String.class);
    }

    public String updateFallback(String name,UserInfo userInfo){
        return "更新失败，请稍后尝试";
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
