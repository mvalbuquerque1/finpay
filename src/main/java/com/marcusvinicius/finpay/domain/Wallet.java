package com.marcusvinicius.finpay.domain;

import com.marcusvinicius.finpay.domain.exceptons.InsufficientBalanceException;

import java.math.BigDecimal;
import java.util.Currency;

public class Wallet {

    private BigDecimal balance;
    private Currency currency;

    public void credit(BigDecimal amount) {
        balance = balance.add(amount);
    }

    public void debit(BigDecimal amount) throws InsufficientBalanceException {
        if (amount.compareTo(balance) > 0) {
            throw new InsufficientBalanceException("Amount bigger than balance");
        }
        balance = balance.subtract(amount);
    }

}
