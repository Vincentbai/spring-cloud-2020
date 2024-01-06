package au.com.vincentbai.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author vincentbai
 * @date 5/01/2024 3:50 pm
 * @description Life is a journey, learn to enjoy the ride. ❤️
 */

@EnableDiscoveryClient
@SpringBootApplication
class AlibabaProviderPayment9001Application {

    public static void main(String[] args) {
        SpringApplication.run(AlibabaProviderPayment9001Application.class, args);
        System.out.println("AlibabaProviderPayment9001Application started!!!");
    }
}
