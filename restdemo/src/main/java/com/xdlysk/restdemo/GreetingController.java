package com.xdlysk.restdemo;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping(path = "/greeting")
public class GreetingController {

    private static final String template = "Hello ,%s!";

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/greeting/name",method = RequestMethod.GET)
    public Greeting greeting(@RequestParam(value = "name",defaultValue = "World")String name){
        return new Greeting(counter.incrementAndGet(),String.format(template,name));
    }

    @RequestMapping(value = "/id/{id}")
    String getIdByValue(@PathVariable("id") String personId) {
        return personId;
    }
}
