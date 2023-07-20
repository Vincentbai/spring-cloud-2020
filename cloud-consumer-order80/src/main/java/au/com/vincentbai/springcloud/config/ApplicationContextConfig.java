package au.com.vincentbai.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    // applicationContext.xml <bean id=“” class="">
    @Bean
    @LoadBalanced // 为 RestTemplate 赋予了负载均衡的能力
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
