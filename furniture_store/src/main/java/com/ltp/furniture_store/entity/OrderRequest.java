package com.ltp.furniture_store.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private Integer customerId;
    private double totalPrice;
    private boolean delivery;
    private String address;
    private List<OrderItemRequest> items;
}
