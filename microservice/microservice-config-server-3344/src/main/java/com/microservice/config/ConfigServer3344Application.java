package com.microservice.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class ConfigServer3344Application {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServer3344Application.class,args);
    }
}
