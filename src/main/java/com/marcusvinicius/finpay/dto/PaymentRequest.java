package com.marcusvinicius.finpay.dto;

import com.marcusvinicius.finpay.util.enums.PaymentMethod;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentRequest(@NotNull UUID userId,
                             @NotNull @DecimalMin(value = "0.01", message = "Amount must be greater than zero") BigDecimal amount,
                             @NotNull @Pattern(regexp = "^(R\\$|\\$)$", message = "Currency must be R$ or $") String currency,
                             @NotNull PaymentMethod paymentMethod) {
    public PaymentRequest {
        if (userId == null) {
            throw new IllegalArgumentException("UserId must not be null");
        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        if (currency == null || currency.isBlank()) {
            throw new IllegalArgumentException("Currency must not be null or blank");
        }
        if (paymentMethod == null) {
            throw new IllegalArgumentException("PaymentMethod must not be null");
        }
    }
}