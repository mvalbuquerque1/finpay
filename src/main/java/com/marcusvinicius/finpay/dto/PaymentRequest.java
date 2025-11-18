package com.marcusvinicius.finpay.dto;

import com.marcusvinicius.finpay.domain.Transaction;
import com.marcusvinicius.finpay.util.enums.PaymentMethod;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

public record PaymentRequest(
        @NotNull UUID userId,
        @NotNull @DecimalMin(value = "0.01", message = "Amount must be greater than zero") BigDecimal amount,
        @NotNull @Pattern(regexp = "^(R\\$|\\$)$", message = "Currency must be R$ or $") String currency,
        @NotNull PaymentMethod paymentMethod
) {
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
    public Transaction toDomain() {
        Currency domainCurrency = mapToCurrency(this.currency);
        return Transaction.pending(this.amount, domainCurrency);
    }

    private Currency mapToCurrency(String symbol) {
        return switch (symbol) {
            case "R$" -> Currency.getInstance("BRL");
            case "$" -> Currency.getInstance("USD");
            default -> throw new IllegalArgumentException("Unsupported currency symbol: " + symbol);
        };
    }
}
