package com.marcusvinicius.finpay.dto;

import com.marcusvinicius.finpay.util.enums.PaymentMethod;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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

    @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
    private BigDecimal amount;

    @NotNull
    //TODO Create validation
    @Pattern(regexp = "^(R\\$|\\$)$", message = "Currency must be R$ or $")
    private String currency;

    @NotNull
    private PaymentMethod paymentMethod;

    public PaymentRequest() {
    }

    public PaymentRequest(UUID userId, BigDecimal amount, String currency, PaymentMethod paymentMethod) {
        this.userId = userId;
        this.amount = amount;
        this.currency = currency;
        this.paymentMethod = paymentMethod;
    }
}
