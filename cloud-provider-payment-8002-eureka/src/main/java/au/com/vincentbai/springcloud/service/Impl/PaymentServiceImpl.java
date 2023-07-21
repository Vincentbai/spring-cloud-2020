package au.com.vincentbai.springcloud.service.Impl;

import au.com.vincentbai.springcloud.dao.PaymentDao;
import au.com.vincentbai.springcloud.service.PaymentService;
import au.com.vincentbai.springcloud.entities.Payment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
