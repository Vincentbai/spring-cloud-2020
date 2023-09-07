package au.com.vincentbai.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author vincentbai
 * @date 1/09/2023 10:43 am
 * @description Life is a journey, learn to enjoy the ride. ❤️
 */

@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_Ok(Integer id) {
        return "------PaymentFallbackService paymentInfo_Ok fallback 😢";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "------PaymentFallbackService paymentInfo_Timeout fallback 😢";
    }
}
