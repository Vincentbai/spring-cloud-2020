package au.com.vincentbai.springcloud.service;

import au.com.vincentbai.springcloud.entities.Payment;
import org.springframework.stereotype.Service;

// 接口不能生成对象，所以不需要注解
public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(Long id);


}
