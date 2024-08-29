package com.ltp.furniture_store.web;

import com.ltp.furniture_store.entity.Order;
import com.ltp.furniture_store.entity.OrderRequest;
import com.ltp.furniture_store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest orderRequest) {
        System.out.println("Received OrderRequest: " + orderRequest);

        try {
            Order order = orderService.createOrderFromCart(
                    orderRequest.getCustomerId(),
                    orderRequest.getTotalPrice(),
                    orderRequest.isDelivery(),
                    orderRequest.getAddress(),
                    orderRequest.getItems() // Pass the items list
            );
            return  ResponseEntity.status(HttpStatus.CREATED).body(order);
        } catch (RuntimeException e) {
            System.err.println("Error creating order: " + e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }




    @PostMapping("/update-address")
    public ResponseEntity<Order> updateOrderAddress(@RequestParam Integer orderId, @RequestParam String address) {
        try {
            Order order = orderService.updateOrderAddress(orderId, address);
            return ResponseEntity.ok(order);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
