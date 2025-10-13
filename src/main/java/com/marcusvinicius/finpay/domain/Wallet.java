package com.marcusvinicius.finpay.domain;

import com.marcusvinicius.finpay.domain.exceptions.InsufficientBalanceException;

import java.math.BigDecimal;
import java.util.Currency;

public class Wallet {

    private BigDecimal balance;
    private Currency currency;

    public Wallet(BigDecimal initialBalance, Currency currency) {
        this.balance = initialBalance != null ? initialBalance : BigDecimal.ZERO;
        this.currency = currency;
    }

    public void credit(BigDecimal amount, Currency currency) {

        if (amount == null) {
            throw new IllegalArgumentException("Amount must not be null");
        }
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount must not be negative");
        }
        if (!this.currency.equals(currency)) {
            throw new IllegalArgumentException("Currency mismatch: expected " + this.currency + " but got " + currency);
        }
        balance = balance.add(amount);
    }

    public void debit(BigDecimal amount, Currency currency) throws InsufficientBalanceException {
        if (!this.currency.equals(currency)) {
            throw new IllegalArgumentException("Currency mismatch: expected " + this.currency + " but got " + currency);
        }
        if (amount == null) {
            throw new IllegalArgumentException("Amount must not be null");
        }
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount must not be negative");
        }
        if (amount.compareTo(balance) > 0) {
            throw new InsufficientBalanceException("Insufficient balance: attempted to debit " + amount + " but current balance is " + balance);
        }
        balance = balance.subtract(amount);

}
