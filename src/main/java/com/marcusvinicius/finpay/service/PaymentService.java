package com.marcusvinicius.finpay.service;

import com.marcusvinicius.finpay.dto.PaymentRequest;
import com.marcusvinicius.finpay.dto.PaymentResponse;

public interface PaymentService {

    PaymentResponse doPayment(PaymentRequest request);
}
