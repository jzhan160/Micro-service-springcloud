package com.example.eureka7003;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class EurekaServerApplication7003 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication7003.class, args);
    }

}
