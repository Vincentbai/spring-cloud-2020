package au.com.vincentbai.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

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

}
