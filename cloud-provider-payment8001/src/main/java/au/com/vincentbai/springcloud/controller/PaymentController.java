package au.com.vincentbai.springcloud.controller;

import au.com.vincentbai.springcloud.entities.CommonResult;
import au.com.vincentbai.springcloud.entities.Payment;
import au.com.vincentbai.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    // @RequestBody 将请求体中的JSON/XML数据转换成 Payment 对象
    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){

        int result = paymentService.create(payment);
        log.info("******** insert result: " + result);

        if(result > 0){
            return new CommonResult(200, "Insert payment successfully!", result);
        }else{
            return new CommonResult(444, "Insert payment failed", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){

        Payment payment = paymentService.getPaymentById(id);

        log.info("******** get result: " + payment);

        if(payment != null){
            return new CommonResult(200, "Get payment successfully!", payment);
        }else{
            return new CommonResult(444, "Get payment failed, no payment with this ID!", null);
        }
    }
}
