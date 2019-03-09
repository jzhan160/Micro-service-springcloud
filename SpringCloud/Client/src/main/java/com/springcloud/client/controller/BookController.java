package com.springcloud.client.controller;

import com.jc.service.HelloService;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController implements HelloService {
    @Override
    public String hello(@RequestParam("name") String name)
    {
        System.out.println("==========================");

        return "hello " + name + "!";
    }

    @Override
    public String hello(@RequestHeader("name") String name,
                        @RequestHeader("author") String author,
                        @RequestHeader("price") Integer price){
        System.out.println("==========================q");
        return name+" "+author+" "+price;
    }

}
