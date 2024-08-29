package com.ltp.furniture_store.repository;

import com.ltp.furniture_store.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
