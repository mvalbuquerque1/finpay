package com.marcusvinicius.finpay.domain;

import com.marcusvinicius.finpay.domain.exceptions.InsufficientBalanceException;

import java.math.BigDecimal;
import java.util.Currency;

public class Wallet {

    private BigDecimal balance;
    private Currency currency;

    public Wallet(BigDecimal balance) {
        this.balance = BigDecimal.ZERO;
    }

    public Wallet(BigDecimal initialBalance, Currency currency) {
        this.balance = initialBalance != null ? initialBalance : BigDecimal.ZERO;
        this.currency = currency;
    }

    public void credit(BigDecimal amount, Currency currency) {
        if (!this.currency.equals(currency)) {
            throw new IllegalArgumentException("Currency mismatch: expected " + this.currency + " but got " + currency);
        }
        balance = balance.add(amount);
    }

    public void debit(BigDecimal amount, Currency currency) throws InsufficientBalanceException {
        if (!this.currency.equals(currency)) {
            throw new IllegalArgumentException("Currency mismatch: expected " + this.currency + " but got " + currency);
        }
        if (amount.compareTo(balance) > 0) {
            throw new InsufficientBalanceException("Amount bigger than balance");
        }
        balance = balance.subtract(amount);
    }

}
