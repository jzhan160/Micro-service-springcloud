package com.springboot.ribbonconsumer;

import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
//注解，表示该应用是一个Eureka客户端应用，这样该应用就自动具备了发现服务的能力
@SpringBootApplication
@EnableCircuitBreaker
//开启断路器功能
//@SpringCloudApplication 可以代替以上三个注解
public class RibbonconsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RibbonconsumerApplication.class, args);
	}

	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	//异步请求
	@Bean
	public HystrixCommandAspect hystrixCommandAspect() {
		return new HystrixCommandAspect();
	}
}

