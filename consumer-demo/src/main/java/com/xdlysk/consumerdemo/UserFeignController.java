package com.xdlysk.consumerdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserFeignController {

    @Autowired
    private IUserFeignClient userFeignClient;

    @GetMapping("/userfeign/{name}")
    public UserInfo get(@PathVariable("name") String name){
        return userFeignClient.get(name);
    }

    @GetMapping("/userfeign/map/{name}")
    public UserInfo get2(@PathVariable("name") String name){
        HashMap<String, Object> pmap = new HashMap<>();
        pmap.put("name",name);
        pmap.put("birthday","2001-1-1");
        return userFeignClient.get2(pmap);
    }

    @PostMapping(value = "/userfeign")
    public UserInfo post(@RequestBody UserInfo user){
        return userFeignClient.post(user);
    }
}
