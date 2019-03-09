package com.springboot.ribbonconsumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CacheService {
    @Autowired
    private RestTemplate restTemplate;
    //会自动开启缓存，默认所有的参数都将作为缓存的key，如果在某次调用中传入的两个参数和之前传入的两个参数
    // 都一致的话，则直接使用缓存，否则就发起请求，
    @CacheResult(cacheKeyMethod = "getCacheKey2")// 指定缓存方法，此时默认的规则失效。
    //@CacheResult
    @HystrixCommand
    public String testCache(/*@CacheKey用来指定单个缓存参数，如果之前使用cacheKeyMethod的话
    cacheKey则失效*/ Integer id) {
        return restTemplate.getForObject("http://HELLO-SERVICE/getbook/{1}", String.class, id);
    }
    public String getCacheKey2(Integer id) {
        return String.valueOf(id);
    }


    @CacheRemove(commandKey = "testCache") //指定需要移除的缓存
    @HystrixCommand
    public String  testRemoveCache(@CacheKey Integer id) {
        return null;
    }
}
