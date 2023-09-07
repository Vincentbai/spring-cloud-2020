package au.com.vincentbai.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentInfo_OK(Integer id){
        return  "线程池： " + Thread.currentThread().getName() + " paymentInfo_OK,  " + id + "\t" + "😄";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_timeout_handler", commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="3000")
    })
    public String paymentInfo_timeout(Integer id){
        int timeNumber = 5;
//        int age = 10 / 0;
        try{ TimeUnit.SECONDS.sleep(timeNumber); }catch (InterruptedException e){e.printStackTrace();}
        return  "线程池： " + Thread.currentThread().getName() + " paymentInfo_Timeout,  " + id + "\t" + "😢 耗时" + timeNumber + "秒钟";
    }

    public String paymentInfo_timeout_handler(Integer id){
        return  "线程池： " + Thread.currentThread().getName() + " paymentInfo_Timeout_handler,  " + id + "\t" + "🤯";
    }

    /**
     * 服务熔断 Circuit Breaker
     * The precise way that the circuit opening and closing occurs is as follows：
     * 1. Assuming the volume across a circuit meets a certain threshold :
     *      HystrixCommandProperties.circuitBreakerRequestVolumeThreshold()
     * 2. And assuming that the error percentage, as defined above exceeds the error percentage defined in :
     *      HystrixCommandProperties.circuitBreakerErrorThresholdPercentage()
     * 3. Then the circuit-breaker transitions from CLOSED to OPEN.
     * 4. While it is open, it short-circuits all requests made against that circuit-breaker.
     * 5. After some amount of time (
     *      HystrixCommandProperties.circuitBreakerSleepWindowInMilliseconds()
     *    ), the next request is let through. If it fails, the command stays OPEN for the sleep window. If it succeeds,
     *    it transitions to CLOSED and the logic in 1) takes over again.
     * If you include your unit test, I can look at your specific sequence of requests and understand better what you'd like to see changed.
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name="circuitBreaker.enabled", value="true"), // 是否开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="10"), // 10次请求以上才启动断路器
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="10000"), // 时间窗口期
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="60"), // 失败率阈值
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){

        if(id<0){
            throw new RuntimeException("********* 模拟报错！");
        }

        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName() + "\t" + "success, transaction ID:" + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "ID can't be negative， please try again later! 😢 ID:" + id;
    }

}
