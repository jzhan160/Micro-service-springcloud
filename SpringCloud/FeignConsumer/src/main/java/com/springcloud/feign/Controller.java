package com.springcloud.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
public class Controller {
    @Autowired
    HelloService helloService;


    @RequestMapping("/hello")
    public String hello() {
        return helloService.hello();
    }


    @RequestMapping("/hello1")
    public String hello1() {
        return helloService.hello("Jiacheng");
    }

    @RequestMapping(value = "/hello2")
    public String hello2() {
        String book = helloService.hello("CS",
                "zzz", 33);
        System.out.println(book);
        return book;
    }




}
