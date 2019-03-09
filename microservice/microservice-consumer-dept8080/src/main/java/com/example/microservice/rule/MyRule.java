package com.example.microservice.rule;

import com.netflix.loadbalancer.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//user-defined rules can not be the same or sub package of @ComponentScan
//round each for 5 times
@Configuration
public class MyRule {

    @Bean
    public IRule userRoundRule(){
        return new Round5Times();
    }
}
