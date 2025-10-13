package com.marcusvinicius.finpay.dto;

import com.marcusvinicius.finpay.util.enums.PaymentStatus;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PaymentResponse {

    public PaymentResponse(UUID paymentId, PaymentStatus status) {
        this.paymentId = paymentId;
        this.status = status;
    }

    private UUID paymentId;
    private PaymentStatus status;
}
