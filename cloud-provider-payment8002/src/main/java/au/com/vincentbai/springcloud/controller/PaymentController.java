package au.com.vincentbai.springcloud.controller;

import au.com.vincentbai.springcloud.entities.CommonResult;
import au.com.vincentbai.springcloud.entities.Payment;
import au.com.vincentbai.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);

        if(result > 0){

            return new CommonResult(200, "Create payment successfully! Port: " + serverPort, result);

        }else {

            return new CommonResult(444, "Create payment failed! Port: " + serverPort, null);

        }

    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){

        Payment payment = paymentService.getPaymentById(id);

        if( payment != null){

            return new CommonResult(200, "Get payment successfully! Port: " + serverPort, payment);

        }else {

            return new CommonResult(444, "Get payment failed! Port: " + serverPort, null);

        }

    }

}
