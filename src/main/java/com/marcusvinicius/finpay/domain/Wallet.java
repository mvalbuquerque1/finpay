package com.marcusvinicius.finpay.domain;

import com.marcusvinicius.finpay.domain.exceptions.InsufficientBalanceException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.List;

public class Wallet {

    private final Account account;
    private final Currency currency;
    private BigDecimal balance;
    private final List<Transaction> transactions = new ArrayList<>();

    public Wallet(Account account, Currency currency, BigDecimal initialBalance) {
        validate(account, currency, initialBalance);
        this.account = account;
        this.currency = currency;
        this.balance = initialBalance;
    }

    private void validate(Account account, Currency currency, BigDecimal balance) {
        if (account == null) {
            throw new IllegalArgumentException("Account must not be null");
        }
        if (currency == null) {
            throw new IllegalArgumentException("Currency must not be null");
        }
        if (balance == null || balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Initial balance must not be null or negative");
        }
    }

    public Account getAccount() {
        return account;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }


    public void credit(BigDecimal amount) {
        validateAmount(amount);
        balance = balance.add(amount);
        Transaction tx = Transaction.success(amount, currency);
        transactions.add(tx);
    }

    public void debit(BigDecimal amount) throws InsufficientBalanceException {
        validateAmount(amount);
        if (balance.compareTo(amount) < 0) {
            //TODO check if this is the best way to handle failed transactions
            Transaction tx = Transaction.failed(amount, currency);
            transactions.add(tx);
            throw new InsufficientBalanceException("Insufficient balance for debit operation");
        }
        balance = balance.subtract(amount);
        Transaction tx = Transaction.success(amount.negate(), currency);
        transactions.add(tx);
    }

    private void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
    }
}
