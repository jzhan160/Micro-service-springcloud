package com.microservice.api.service;

import com.microservice.api.entity.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService> {
    @Override
    public DeptClientService create(Throwable throwable) {
        return new DeptClientService() {
            @Override
            public Integer add_dept(Dept dept) {
                return null;
            }

            @Override
            public List<Dept> findAll() {
                return null;
            }

            @Override
            public Dept findOne(Integer id) {
                return new Dept(id,"error","degrade");
            }
        };
    }
}
