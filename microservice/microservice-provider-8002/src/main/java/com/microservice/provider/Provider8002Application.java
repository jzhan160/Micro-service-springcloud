package com.microservice.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.microservice.provider.mapper")
@ComponentScan("com.microservice.provider.*")
@SpringBootApplication
@EnableEurekaClient //启动时自动注册
public class Provider8002Application {
    public static void main(String[] args) {
             SpringApplication.run(Provider8002Application.class, args);

    }
}
