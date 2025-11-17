package com.marcusvinicius.finpay.domain;

import com.marcusvinicius.finpay.util.enums.TransactionStatus;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;
import java.util.UUID;

public class Transaction {

    private final UUID transactionId;
    private final BigDecimal amount;
    private final Currency currency;
    private final TransactionStatus status;

    public Transaction(UUID transactionId, BigDecimal amount, Currency currency, TransactionStatus status) {
        validate(transactionId, amount, currency, status);
        this.transactionId = transactionId;
        this.amount = amount;
        this.currency = currency;
        this.status = status;
    }

    private void validate(UUID transactionId, BigDecimal amount, Currency currency, TransactionStatus status) {
        if (transactionId == null) {
            throw new IllegalArgumentException("Transaction ID must not be null");
        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("Amount must not be null or zero");
        }
        if (currency == null) {
            throw new IllegalArgumentException("Currency must not be null");
        }
        if (status == null) {
            throw new IllegalArgumentException("Status must not be null");
        }
    }

    public static Transaction pending(BigDecimal amount, Currency currency) {
        return new Transaction(UUID.randomUUID(), amount, currency, TransactionStatus.PENDING);
    }

    public static Transaction success(BigDecimal amount, Currency currency) {
        return new Transaction(UUID.randomUUID(), amount, currency, TransactionStatus.SUCCESS);
    }

    public static Transaction failed(BigDecimal amount, Currency currency) {
        return new Transaction(UUID.randomUUID(), amount, currency, TransactionStatus.SUCCESS);
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Transaction that)) return false;
        return Objects.equals(transactionId, that.transactionId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(transactionId);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", amount=" + amount +
                ", currency=" + currency +
                ", status=" + status +
                '}';
    }
}
