package com.springcloud.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*

@RestController
public class ControllerUseingInher {
    @Autowired
    HelloService2 helloService2;

    @RequestMapping("/ihello1")
    public String hello1() {
        return helloService2.hello("jiacheng");
    }

    @RequestMapping(value = "/ihello2")
    public String hello2() {
        String book = helloService2.hello("CS", "jc", 33);
        System.out.println(book);
        return book;
    }

}
*/
