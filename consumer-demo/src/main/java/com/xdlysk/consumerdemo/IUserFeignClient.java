package com.xdlysk.consumerdemo;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "Rest-Demo")
public interface IUserFeignClient {

    @GetMapping("/user/{name}")
    public UserInfo get(@PathVariable("name") String name);

    @GetMapping("/user/get2")
    public UserInfo get2(@RequestParam Map<String,Object> map);

    @PostMapping(value = "/user")
    public UserInfo post(@RequestBody UserInfo user);
}
