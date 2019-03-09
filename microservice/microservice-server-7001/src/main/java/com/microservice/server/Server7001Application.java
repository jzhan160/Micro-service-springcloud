package com.microservice.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class })
public class Server7001Application {
    public static void main(String[] args) {
        SpringApplication.run(Server7001Application.class,args);
    }
}
