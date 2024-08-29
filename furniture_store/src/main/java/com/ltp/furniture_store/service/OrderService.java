package com.ltp.furniture_store.service;

import com.ltp.furniture_store.entity.*;
import com.ltp.furniture_store.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private RegisteredCustomerService registeredCustomerService;

    @Autowired
    private CatalogRepository catalogRepository;

    @Autowired
    private ShoppingCartStatusRepository shoppingCartStatusRepository;

    @Autowired
    private OrderItemService orderItemService;

    @Transactional
    public Order createOrderFromCart(Integer customerId, double totalPrice, boolean delivery, String address, List<OrderItemRequest> items) {
        ShoppingCart cart = shoppingCartRepository.findByCustomerAndShoppingCartStatus_StatusDescription(
                        registeredCustomerService.findUserById(customerId), ShoppingCartStatusEnum.ACTIVE)
                .orElseThrow(() -> new RuntimeException("No active cart found for customer"));

        Order order = new Order();
        order.setCustomer(cart.getCustomer());
        order.setTotalPrice(totalPrice);
        order.setDate(new Date());
        order.setDelivery(delivery);
        order.setAddress(address);
        order.setStatus(statusRepository.findByDescriptionStatus(OrderStatusEnum.UNPAID));
        orderRepository.save(order);

        for (OrderItemRequest item : items) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            Catalog catalogItem = catalogRepository.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            orderItem.setCatalog(catalogItem);
            orderItem.setQuantity(item.getQuantity());

            // Save the order item using OrderItemService
            orderItemService.saveOrderItem(orderItem);
        }

        cart.setShoppingCartStatus(shoppingCartStatusRepository.findByStatusDescription(ShoppingCartStatusEnum.COMPLETED));
        shoppingCartRepository.save(cart);

        return order;
    }

    @Transactional
    public Order updateOrderAddress(Integer orderId, String newAddress) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));

        if (order.getDelivery() && "Please update address".equals(order.getAddress())) {
            order.setAddress(newAddress);
            orderRepository.save(order);
        } else {
            throw new RuntimeException("Address update is not allowed.");
        }

        return order;
    }
}
