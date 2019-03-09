package com.microservice.myZuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
 import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class MyZuul9527Application {
    public static void main(String[] args) {
        SpringApplication.run(MyZuul9527Application.class,args);
    }
}
