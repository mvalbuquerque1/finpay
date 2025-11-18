package com.marcusvinicius.finpay.dto;

import com.marcusvinicius.finpay.domain.Transaction;
import com.marcusvinicius.finpay.util.enums.PaymentMethod;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

public record PaymentRequest(@NotNull UUID userId,
                             @NotNull @DecimalMin(value = "0.01", message = "Amount must be greater than zero") BigDecimal amount,
                             @NotNull @Pattern(regexp = "^(R\\$|\\$)$", message = "Currency must be R$ or $") String currency,
                             @NotNull PaymentMethod paymentMethod) {
    // Compact constructor removed: validation is handled by Jakarta Bean Validation annotations.

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