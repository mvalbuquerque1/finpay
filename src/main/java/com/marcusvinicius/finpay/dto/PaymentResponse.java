package com.marcusvinicius.finpay.dto;

import com.marcusvinicius.finpay.util.PaymentStatus;

import java.util.UUID;

public class PaymentResponse {

    public PaymentResponse(UUID paymentId, PaymentStatus status) {
        this.paymentId = paymentId;
        this.status = status;
    }

    private UUID paymentId;
    private PaymentStatus status;
}
