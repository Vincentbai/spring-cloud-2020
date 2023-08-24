package au.com.vincentbai.springcloud.controller;

import au.com.vincentbai.springcloud.entities.CommonResult;
import au.com.vincentbai.springcloud.entities.Payment;
import au.com.vincentbai.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    // @RequestBody 将请求体中的JSON/XML数据转换成 Payment 对象
    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){

        int result = paymentService.create(payment);
        log.info("******** insert result: " + result);

        if(result > 0){
            return new CommonResult(200, "Insert payment successfully! Port: " + serverPort, result);
        }else{
            return new CommonResult(444, "Insert payment failed! Port: " + serverPort, null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){

        Payment payment = paymentService.getPaymentById(id);

        log.info("******** get result: " + payment);

        if(payment != null){
            return new CommonResult(200, "Get payment successfully! Port: " + serverPort, payment);
        }else{
            return new CommonResult(444, "Get payment failed, no payment with this ID! Port: " + serverPort, null);
        }
    }

    @GetMapping("/payment/discovery")
    public Object discovery(){

        List<String> services = discoveryClient.getServices();

        for(String ele: services){
            log.info("************elememt: " + ele);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

        for(ServiceInstance si: instances){
            log.info(si.getServiceId() + "\t" + si.getHost() + "\t" + si.getPort() + "\t" + si.getUri());
        }

        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){ return serverPort; }

    @GetMapping(value="payment/feign/timeout")
    public String paymentFeignTimeout(){
        try{TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e){e.printStackTrace();}
        return serverPort;
    }


}
