package com.marcusvinicius.finpay.util.enums;

//TODO Analisar melhor
public enum PaymentMethod {

    CREDIT_CARD("Card Payment"),
    PIX("Instant Payment"),
    CASH("Cash Payment");

    private final String description;

    PaymentMethod(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
