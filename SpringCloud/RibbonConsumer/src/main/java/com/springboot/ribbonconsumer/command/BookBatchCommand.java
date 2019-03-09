package com.springboot.ribbonconsumer.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.springboot.ribbonconsumer.service.HelloService;

import java.util.List;

public class BookBatchCommand extends HystrixCommand<List<String>> {
    private List<Long> ids;
    private HelloService helloService;

    public BookBatchCommand(List<Long> ids, HelloService bookService) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("CollapsingGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("CollapsingKey")));
        this.ids = ids;
        this.helloService = bookService;
    }

    @Override
    protected List<String> run() throws Exception {
        return helloService.testBookBatch(ids);
    }
}