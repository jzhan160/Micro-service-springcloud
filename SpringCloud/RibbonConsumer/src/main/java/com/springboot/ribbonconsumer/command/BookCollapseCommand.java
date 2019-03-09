package com.springboot.ribbonconsumer.command;

import com.netflix.hystrix.*;
import com.springboot.ribbonconsumer.service.HelloService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class BookCollapseCommand extends HystrixCollapser<List<String>, String, Long> {
    private HelloService helloService;
    private Long id;

    //首先在构造方法中，我们设置了请求时间窗为100ms，即请求时间间隔在100ms之内的请求会被合并为一个请求。
    public BookCollapseCommand(HelloService bookService, Long id) {
        super(Setter.withCollapserKey(HystrixCollapserKey.Factory.
                asKey("bookCollapseCommand")).
                andCollapserPropertiesDefaults(HystrixCollapserProperties.Setter().
                        withTimerDelayInMilliseconds(100)));
        this.helloService = bookService;
        this.id = id;
    }

    @Override
    public Long getRequestArgument() {
        return id;
    }

    //createCommand方法主要用来合并请求，在这里获取到各个单个请求的id，将这些单个的id放到一个集合中，
    // 然后再创建出一个BookBatchCommand对象，用该对象去发起一个批量请求。
    @Override
    protected HystrixCommand<List<String>> createCommand(Collection<CollapsedRequest<String, Long>> collapsedRequests) {
        List<Long> ids = new ArrayList<>(collapsedRequests.size());
        ids.addAll(collapsedRequests.stream().map(CollapsedRequest::getArgument).
                collect(Collectors.toList()));
        BookBatchCommand bookBatchCommand = new BookBatchCommand(ids, helloService);
        return bookBatchCommand;
    }


    //主要用来为每个请求设置请求结果。该方法的第一个参数batchResponse表示批处理请求的结果，
    // 第二个参数collapsedRequests则代表了每一个被合并的请求，然后我们通过遍历batchResponse
    //来为collapsedRequests设置请求结果
    @Override
    protected void mapResponseToRequests(List<String> batchResponse,
                                         Collection<CollapsedRequest<String, Long>> collapsedRequests) {
        System.out.println("mapResponseToRequests");
        int count = 0;
        for (CollapsedRequest<String, Long> collapsedRequest : collapsedRequests) {
            String book = batchResponse.get(count++);
            collapsedRequest.setResponse(book);
        }
    }
}