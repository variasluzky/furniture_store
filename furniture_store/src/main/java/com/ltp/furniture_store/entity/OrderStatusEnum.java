package com.ltp.furniture_store.entity;

public enum OrderStatusEnum {
    UNPAID("Unpaid"),
    PAID("Paid"),
    CANCELLED("Cancelled"),
    COMPLETED("Completed");

    private final String description;

    OrderStatusEnum(String description){
        this.description = description;
    }
    public String getDescription(){
        return description;
    }
}
