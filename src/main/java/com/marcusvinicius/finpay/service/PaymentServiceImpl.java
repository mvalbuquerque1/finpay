package com.marcusvinicius.finpay.service;

import com.marcusvinicius.finpay.util.PaymentStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public String doPayment() {
        String status = PaymentStatus.PENDING.toString();
        String paymentId = UUID.randomUUID().toString();
        return status + "\n " + paymentId;
    }
}
