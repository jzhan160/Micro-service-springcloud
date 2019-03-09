package com.springcloud.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {
    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() {
        List<ServiceInstance> instances = client.getInstances("hello-service");
        for (int i = 0; i < instances.size(); i++) {
            System.out.println("/hello,host:" + instances.get(i).getHost() +
                    ",service_id:" + instances.get(i).getServiceId());
        }
        return "Hello World";
    }

    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    public String hello1(@RequestParam String name) {
        return "hello " + name + "!";
    }

    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
    public String hello2(@RequestHeader String name,
                       @RequestHeader String author,
                       @RequestHeader Integer price) {

        return name+" "+author+" "+price;
    }


    @RequestMapping(value = "/getbook/{id}", method = RequestMethod.GET)
    public String book5(@PathVariable("id") Integer id) {
        System.out.println(">>>>>>>>/getbook/{id}");
        if (id == 1) {
            return new String("book1");
        } else if (id == 2) {
            return new String("book2");
        }
        return new String("book");
    }


    //请求合并
    @RequestMapping("/getBookBatch")
    public List<String> getBookBatch(String ids) {
        System.out.println("ids>>>>>>>>>>>>>>>>>>>>>" + ids);
        ArrayList<String> books = new ArrayList<>();
        books.add(new String("book1"));
        books.add(new String("book2"));
        books.add(new String("book3"));

        return books;
    }

    @RequestMapping("/getBookBatch/{id}")
    public String getBookBatchById(@PathVariable Integer id) {
        String book = new String("book1");
        return book;
    }

}