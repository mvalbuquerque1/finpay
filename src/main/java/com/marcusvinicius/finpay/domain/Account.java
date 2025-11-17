package com.marcusvinicius.finpay.domain;

import com.marcusvinicius.finpay.util.enums.AccountStatus;

import java.util.Objects;
import java.util.UUID;

public class Account {

    private final UUID userId;
    private String name;
    private AccountStatus status;

    public Account(UUID userId, String name, AccountStatus status) {
        validate(userId, name, status);
        this.userId = userId;
        this.name = name;
        this.status = status;
    }

    private void validate(UUID userId, String name, AccountStatus status) {
        if (userId == null) {
            throw new IllegalArgumentException("UserId must not be null");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name must not be null or blank");
        }
        if (status == null) {
            throw new IllegalArgumentException("Status must not be null");
        }
    }

    public UUID getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void rename(String newName) {
        if (newName == null || newName.isBlank()) {
            throw new IllegalArgumentException("Name must not be null or blank");
        }
        this.name = newName;
    }

    public void activate() {
        if (this.status == AccountStatus.ACTIVE) {
            throw new IllegalStateException("Account is already active");
        }
        this.status = AccountStatus.ACTIVE;
    }

    public void block() {
        if (this.status == AccountStatus.BLOCKED) {
            throw new IllegalStateException("Account is already blocked");
        }
        this.status = AccountStatus.BLOCKED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account account)) return false;
        return Objects.equals(userId, account.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "Account{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
