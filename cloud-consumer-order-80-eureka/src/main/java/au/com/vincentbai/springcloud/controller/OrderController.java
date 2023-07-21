package au.com.vincentbai.springcloud.controller;

import au.com.vincentbai.springcloud.entities.CommonResult;
import au.com.vincentbai.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {

    // public static final String PAYMENT_URL = "http://localhost:8001";
    // 调用 Eureka 中payment 微服务在 yml 文件中注册的名称 spring.application.name : CLOUD-PAYMENT-SERVICE
    // 如果两个微服务在application name一样，会在Eureka中自动实现负载均衡
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        log.info("****** consumer: " + payment);

        // 使用了Eureka后，这个写死的URL还需要吗？
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id, CommonResult.class);
    }
}
