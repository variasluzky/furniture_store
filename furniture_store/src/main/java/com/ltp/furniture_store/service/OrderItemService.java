package com.ltp.furniture_store.service;


import com.ltp.furniture_store.entity.OrderItem;
import com.ltp.furniture_store.entity.OrderItemId;
import com.ltp.furniture_store.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    public void saveOrderItem(OrderItem orderItem) {
        if (orderItem.getId() == null) {
            OrderItemId id = new OrderItemId();
            id.setOrderId(orderItem.getOrder().getOrderId()); // Set orderId from Order entity
            id.setOrderItemId(generateNextOrderItemId(orderItem.getOrder().getOrderId()));
            orderItem.setId(id);
        }
        orderItemRepository.save(orderItem);
    }

    private Integer generateNextOrderItemId(Integer orderId) {
        Integer maxOrderItemId = orderItemRepository.findMaxOrderItemIdByOrderId(orderId);
        return (maxOrderItemId != null) ? maxOrderItemId + 1 : 1;
    }
}
