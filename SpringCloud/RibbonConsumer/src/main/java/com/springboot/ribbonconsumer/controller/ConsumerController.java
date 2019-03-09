package com.springboot.ribbonconsumer.controller;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.springboot.ribbonconsumer.command.BookCollapseCommand;
import com.springboot.ribbonconsumer.command.BookCommand;
import com.springboot.ribbonconsumer.command.CustomerCommand;
import com.springboot.ribbonconsumer.service.CacheService;
import com.springboot.ribbonconsumer.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController

public class ConsumerController {
    @Autowired
    private HelloService helloService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CacheService cacheService;

    @RequestMapping(value = "/ribbon-consumer",method = RequestMethod.GET)
    public String helloController() {
        return helloService.hello();
    }

    @RequestMapping("/customerCommand")
    public String test1() throws ExecutionException, InterruptedException {
        CustomerCommand customerCommand =
                new CustomerCommand(HystrixCommand.Setter.withGroupKey
                        (HystrixCommandGroupKey.Factory.asKey("")),
                        restTemplate);
        //同步调用
        //String res = customerCommand.execute();
        //异步调用
        Future<String> queue = customerCommand.queue();
        String result = queue.get();
        return result;
    }


    //异步请求
    @RequestMapping("/testAsyn")
    public String testAsyn() throws ExecutionException, InterruptedException {
        Future<String>
future = helloService.asynRequest();
        //调用get方法时也可以设置超时时长
        return future.get();
    }

    @RequestMapping("/testCache")
    public String test5() {
        HystrixCommandKey commandKey = HystrixCommandKey.Factory.asKey("commandKey");
        HystrixRequestContext.initializeContext();
        BookCommand bc1 = new BookCommand(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")).andCommandKey(commandKey),
                restTemplate, 1l);
        String e1 = bc1.execute();
        BookCommand bc2 = new BookCommand(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")).andCommandKey(commandKey), restTemplate, 1l);
        String e2 = bc2.execute();
        BookCommand bc3 = new BookCommand(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")).andCommandKey(commandKey), restTemplate, 1l);
        String e3 = bc3.execute();
        System.out.println("e1:" + e1);
        System.out.println("e2:" + e2);
        System.out.println("e3:" + e3);
        return e1;
    }

    @RequestMapping("/testCacheWithAnnotation")
    public String testCacheWithAnnotation() {
        HystrixRequestContext.initializeContext();
        //第一次发起请求
        String b1 = cacheService.testCache(2);
        //参数和上次一致，使用缓存数据
        String b2 = cacheService.testCache(2);
        //参数不一致，发起新请求
       // String b3 = cacheService.testCache(2, "aa");
        return b1;
    }

    @RequestMapping("/testRemoveCache")
    public String test6() {
        HystrixRequestContext.initializeContext();
        //第一次发起请求
        String b1 = cacheService.testCache(2);
        //清除缓存
        cacheService.testRemoveCache(2);
        //缓存被清除，重新发起请求
        String b2 = cacheService.testCache(2);
        //参数一致，使用缓存数据
        String b3 = cacheService.testCache(2);
        return b1;
    }


    @RequestMapping("/testBookBatch")
    @ResponseBody
    public void testBookBatch() throws ExecutionException, InterruptedException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        BookCollapseCommand bc1 = new BookCollapseCommand(helloService, 1l);
        BookCollapseCommand bc2 = new BookCollapseCommand(helloService, 2l);
        BookCollapseCommand bc3 = new BookCollapseCommand(helloService, 3l);
        BookCollapseCommand bc4 = new BookCollapseCommand(helloService, 4l);
        Future<String> q1 = bc1.queue();
        Future<String> q2 = bc2.queue();
        Future<String> q3 = bc3.queue();
        String book1 = q1.get();
        String book2 = q2.get();
        String book3 = q3.get();
        Thread.sleep(3000);
        Future<String> q4 = bc4.queue();
        String book4 = q4.get();
        System.out.println("book1>>>"+book1);
        System.out.println("book2>>>"+book2);
        System.out.println("book3>>>"+book3);
        System.out.println("book4>>>"+book4);
        context.close();
    }
}
