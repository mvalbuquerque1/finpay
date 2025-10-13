package com.marcusvinicius.finpay.service;

import com.marcusvinicius.finpay.dto.PaymentRequest;
import com.marcusvinicius.finpay.dto.PaymentResponse;
import com.marcusvinicius.finpay.util.enums.PaymentStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public PaymentResponse doPayment(PaymentRequest request) {
        UUID paymentId = UUID.randomUUID();
        PaymentStatus status = PaymentStatus.PENDING;
        return new PaymentResponse(paymentId, status);
    }
}
