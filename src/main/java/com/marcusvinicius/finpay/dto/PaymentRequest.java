package com.marcusvinicius.finpay.dto;

import com.marcusvinicius.finpay.util.PaymentMethod;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Currency;

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
    @Currency(value = "R$")
    @Currency(value = "$")
    private String currency;

    @NotNull
    private PaymentMethod paymentMethod;

    public PaymentRequest(UUID userId, BigDecimal amount, String currency, PaymentMethod paymentMethod) {
        this.userId = userId;

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount should be bigger than zero");
        }
        this.amount = amount;

        this.currency = currency;
        this.paymentMethod = paymentMethod;
    }
}
