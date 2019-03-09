package com.springboot.ribbonconsumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

@Service
public class HelloService {
    @Autowired
    private RestTemplate restTemplate;

    //指定断路之后的回调方法
    //同步请求
    /*Spring Cloud中采取的默认负载均衡策略就是轮询，所以当一个服务提供者关掉之后，
    刷新的时候服务请求成功和请求失败是成对出现的：当服务消费者去请求那个被关掉的服务提供者的时候就会请求失败，
    当服务消费者去请求正常的服务提供者时就能获得期望的结果。请求失败时不能给用户展示这样一个ErrorPage,
    而应该是一个可控的页面。*/
    @HystrixCommand(fallbackMethod = "error",ignoreExceptions = ArithmeticException.class)
    public String hello() {
        ResponseEntity<String> responseEntity =
                restTemplate.getForEntity("http://HELLO-SERVICE/hello", String.class);
        return responseEntity.getBody();
    }
    /*
    * fallbackMethod所描述的函数实际上就是一个备胎，用来实现服务的降级处理，
    * 在注解中我们可以通过fallbackMethod属性来指定降级处理的方法名称，
    * 在自定义Hystrix请求命令时我们可以通过重写getFallback函数来处理服务降级之后的逻辑。
    * 使用注解来定义服务降级逻辑时，服务降级函数和@HystrixCommand注解要处于同一个类中，
    * 同时，服务降级函数在执行过程中也有可能发生异常，所以也可以给服务降级函数添加‘备胎’，*/
    public String error(Throwable throwable) {
        System.out.println(throwable.getMessage());
        return "error";
    }

    @HystrixCommand
    public Future<String> asynRequest() {
        return new AsyncResult<String>() {
            @Override
            public String invoke() {
                return restTemplate.getForObject("http://HELLO-SERVICE/hello", String.class);
            }
        };
    }

   //请求合并
    public String testBatchById(Long id) {
        return restTemplate.getForObject("http://HELLO-SERVICE/getBookBatch/{1}",
                String.class, id);
    }

    public List<String> testBookBatch(List<Long> ids) {
        System.out.println("testBookBatch---------"+ids+"Thread.currentThread().getName():" +
                Thread.currentThread().getName());
        String[] books = restTemplate.getForObject("http://HELLO-SERVICE/getBookBatch?ids={1}",
                String[].class, StringUtils.join(ids, ","));
        return Arrays.asList(books);
    }
}
