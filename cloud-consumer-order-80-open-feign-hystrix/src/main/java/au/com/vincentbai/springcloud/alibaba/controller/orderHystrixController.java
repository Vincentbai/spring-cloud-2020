package au.com.vincentbai.springcloud.alibaba.controller;

import au.com.vincentbai.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
// 如果没有配置fallback 方法， 那么就调用这个默认的
@DefaultProperties(defaultFallback = "paymentGlobalFallbackMethod")
public class orderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_Ok(@PathVariable("id") Integer id){
        return paymentHystrixService.paymentInfo_Ok(1);
    }

    @HystrixCommand(fallbackMethod = "paymentTimeoutFallbackMethod", commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="1500")
    })
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable("id") Integer id){
//        int age = 10/0;
        return paymentHystrixService.paymentInfo_Timeout(1);
    }

    // 是paymentInfo_Timeout 的 fallback 方法
    public String paymentTimeoutFallbackMethod(@PathVariable("id") Integer id){

        return "The message from port 80: server is busy now, please try again later! Parameter: " + id;

    }

    @HystrixCommand
    @GetMapping("/consumer/payment/hystrix/error/{id}")
    public String paymentInfo_error(@PathVariable("id") Integer id){

        int age = 10/0;

        return "paymentInfo_error return message!";

    }

    // 下面是全局的 fallback 方法
    public String paymentGlobalFallbackMethod(){
        return "Global fallback method message, please try again later! 🪲";
    }
}
