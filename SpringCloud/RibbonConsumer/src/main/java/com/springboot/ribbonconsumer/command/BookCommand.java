package com.springboot.ribbonconsumer.command;

import com.netflix.hystrix.HystrixCommand;
import org.springframework.web.client.RestTemplate;

public class BookCommand extends HystrixCommand<String> {

    private RestTemplate restTemplate;
    private Long id;

    @Override
    protected String getFallback() {
        Throwable executionException = getExecutionException();
        System.out.println(executionException.getMessage());
        return new String(" book");
    }

    @Override
    protected String run() throws Exception {
        return restTemplate.getForObject("http://HELLO-SERVICE/getbook/{1}", String.class,id);
    }

    public BookCommand(Setter setter, RestTemplate restTemplate,Long id) {
        super(setter);
        this.restTemplate = restTemplate;
        this.id = id;
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(id);
    }
}
