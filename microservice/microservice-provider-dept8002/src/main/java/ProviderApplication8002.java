
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.example.microservice.mapper")
@ComponentScan("com.example.microservice.*")
@SpringBootApplication
@EnableEurekaClient //启动时自动注册
@EnableDiscoveryClient
public class ProviderApplication8002 {
    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication8002.class, args);
    }
}

