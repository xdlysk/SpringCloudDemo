package com.xdlysk.restdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/user")
public class MoreRestfulController {

    //注入配置的端口参数
    @Value("${server.port}")
    private int port;

    @RequestMapping(value = "/{name}" , method = RequestMethod.GET)
    public UserInfo get(@PathVariable("name")String name){
        UserInfo userInfo = new UserInfo();
        userInfo.setBirthday("2018-1-1");
        userInfo.setMobile("13813813813");
        userInfo.setName(name+":"+port);//输出时带上端口
        return userInfo;
    }

    @RequestMapping(value = "/get2" , method = RequestMethod.GET)
    public UserInfo get2(@RequestParam Map<String,Object> map){
        UserInfo userInfo = new UserInfo();
        userInfo.setBirthday(map.get("birthday").toString());
        userInfo.setMobile("13813813813");
        userInfo.setName(map.get("name")+":"+port);
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
