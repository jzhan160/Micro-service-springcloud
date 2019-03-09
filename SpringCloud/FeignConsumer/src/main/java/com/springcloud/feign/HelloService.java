package com.springcloud.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@FeignClient(value="hello-service", fallback = HelloServiceFallback.class)
public interface HelloService {
    @RequestMapping("/hello")
    String hello();

    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    String hello(@RequestParam("name") String name);

    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
    String hello(@RequestHeader("name") String name,
                 @RequestHeader("author") String author,
                 @RequestHeader("price") Integer price);
    //在SpringMVC中，@RequestParam和@RequestHeader注解，如果我们不指定value，
    // 则默认采用参数的名字作为其value，但是在Feign中，这个value必须明确指定，否则会报错
}
