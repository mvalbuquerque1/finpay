package com.marcusvinicius.finpay.dto;

import com.marcusvinicius.finpay.util.PaymentMethod;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class PaymentRequest {

    @NotNull
    private UUID userId;

    @DecimalMin("0.01")
    private BigDecimal amount;

    @NotNull
    private String currency;

    @NotNull
    private PaymentMethod paymentMethod;

    public PaymentRequest(UUID userId, BigDecimal amount, String currency, PaymentMethod paymentMethod) {
        this.userId = userId;
        this.amount = amount;
        this.currency = currency;
        this.paymentMethod = paymentMethod;
    }
}
