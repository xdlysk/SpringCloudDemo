package com.xdlysk.restdemo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
public class MoreRestfulController {

    @RequestMapping(value = "/{name}" , method = RequestMethod.GET)
    public UserInfo get(@PathVariable("name")String name){
        UserInfo userInfo = new UserInfo();
        userInfo.setBirthday("2018-1-1");
        userInfo.setMobile("13813813813");
        userInfo.setName(name);
        return userInfo;
    }

    @RequestMapping(method = RequestMethod.POST)
    public UserInfo post(@RequestBody UserInfo userInfo){
        return userInfo;
    }

    @RequestMapping(value ="/{name}" , method = RequestMethod.DELETE)
    public String delete(@PathVariable("name")String name){
        return "you delete " + name;
    }

    @RequestMapping(value = "/{name}",method = RequestMethod.PUT)
    public String put(@PathVariable("name")String name,@RequestBody UserInfo userInfo){
        return "you update " + name;
    }
}
