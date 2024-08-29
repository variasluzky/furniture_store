package com.ltp.furniture_store.web;

import com.ltp.furniture_store.entity.OrderItem;
import com.ltp.furniture_store.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;  // השתמש בשם המתאים

    @GetMapping("/{orderId}")
    public ResponseEntity<Map<String, Object>> getOrderDetails(@PathVariable Integer orderId) {
        List<OrderItem> orderItems = paymentService.getOrderItemsByOrderId(orderId);
        Double totalPrice = paymentService.calculateTotalPrice(orderId);

        Map<String, Object> response = new HashMap<>();
        response.put("orderItems", orderItems);
        response.put("totalPrice", totalPrice);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}