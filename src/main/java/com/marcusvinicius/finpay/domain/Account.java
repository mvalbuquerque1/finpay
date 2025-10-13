package com.marcusvinicius.finpay.domain;

import com.marcusvinicius.finpay.util.enums.AccountStatus;

import java.util.UUID;

public class Account {

    private final UUID userId;
    private String name;
    private AccountStatus status;

    public Account(UUID userId, String name, AccountStatus status) {
        validate(userId, status);
        this.userId = UUID.randomUUID();
        this.name = name;
        this.status = status;
    }

    private void validate(UUID userId, AccountStatus status) {
        if (userId == null) {
            throw new IllegalArgumentException("Id must not be null");
        }
        if (status.getDescription() == null || status.getDescription().isBlank()) {
            throw new IllegalArgumentException("Status must not be null");
        }
    }

    public UUID getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }
}
