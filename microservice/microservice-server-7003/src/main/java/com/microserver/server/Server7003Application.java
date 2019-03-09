package com.microserver.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class })
public class Server7003Application {
    public static void main(String[] args) {
        SpringApplication.run(Server7003Application.class,args);
    }
}
