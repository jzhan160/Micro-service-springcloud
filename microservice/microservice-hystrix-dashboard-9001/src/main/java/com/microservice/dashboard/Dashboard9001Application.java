package com.microservice.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class Dashboard9001Application {
    public static void main(String[] args) {
        SpringApplication.run(Dashboard9001Application.class,args);
    }
}
