
package com.ltp.furniture_store.repository;

import com.ltp.furniture_store.entity.OrderItem;
import com.ltp.furniture_store.entity.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface paymentRepository extends JpaRepository<OrderItem, OrderItemId> {
    List<OrderItem> findByOrder_OrderId(Integer orderId);
}
