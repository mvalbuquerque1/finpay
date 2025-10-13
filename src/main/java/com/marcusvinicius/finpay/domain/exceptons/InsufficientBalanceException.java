package com.marcusvinicius.finpay.domain.exceptons;

public class InsufficientBalanceException extends Throwable {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
