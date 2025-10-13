package com.marcusvinicius.finpay.util.enums;

public enum AccountStatus {
    ACTIVE("User Active"),
    BLOCKED("User Blocked");

    private final String description;

    AccountStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
