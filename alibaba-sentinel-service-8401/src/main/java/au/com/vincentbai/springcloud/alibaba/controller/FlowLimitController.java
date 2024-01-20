package au.com.vincentbai.springcloud.alibaba.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author vincentbai
 * @date 15/01/2024 3:32 pm
 * @description Life is a journey, learn to enjoy the ride. ❤️
 */
@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA(){
        return "------------testA";
    }

    @GetMapping("/testB")
    public String testB(){
        return "------------testB";
    }

    @GetMapping("/testRT")
    public String testRT(){
        try{ TimeUnit.SECONDS.sleep(1); }catch(InterruptedException e){e.printStackTrace();}
        log.info("testD 测试RT");
        return "------------testRT";
    }
}
