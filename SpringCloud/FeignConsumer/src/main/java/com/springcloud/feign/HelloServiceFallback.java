package com.springcloud.feign;
import com.jc.service.HelloService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class HelloServiceFallback implements HelloService{

    @Override
    public String hello(String s) {
        return "error";
    }

    @Override
    public String hello(String name, String author, Integer price) {
        return name+" error";
    }


}
