package au.com.vincentbai.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author vincentbai
 * @date 15/01/2024 3:29 pm
 * @description Life is a journey, learn to enjoy the ride. ❤️
 */
@EnableDiscoveryClient
@SpringBootApplication
public class AlibabaSentinelService8401Application {

    public static void main(String[] args) {

        SpringApplication.run(AlibabaSentinelService8401Application.class);
        System.out.println("AlibabaSentinelService8401Application started!!!");

    }

}
