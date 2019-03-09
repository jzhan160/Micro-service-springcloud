package com.springboot.ribbonconsumer.command;

import com.netflix.hystrix.HystrixCommand;
import org.springframework.web.client.RestTemplate;

public class CustomerCommand extends HystrixCommand<String>{
    private RestTemplate restTemplate;

    public CustomerCommand(Setter setter, RestTemplate restTemplate) {
        super(setter);
        this.restTemplate = restTemplate;
    }

    //请求调用方法
    @Override
    protected String run() throws Exception {
        return restTemplate.getForObject("http://HELLO-SERVICE/hello", String.class);
    }

    //失败回调方法
    @Override
    protected String getFallback() {
        //抛异常
        Throwable executionException = getExecutionException();
        System.out.println(executionException.getMessage());
        return "feedback";
    }
}
