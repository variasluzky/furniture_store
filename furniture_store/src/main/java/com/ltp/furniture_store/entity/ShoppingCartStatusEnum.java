package com.ltp.furniture_store.entity;

public enum ShoppingCartStatusEnum {
    ACTIVE("Active"),
    CANCELLED("Cancelled"),
    COMPLETED("Completed");

    private final String description;

    ShoppingCartStatusEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
