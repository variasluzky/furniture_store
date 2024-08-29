package com.ltp.furniture_store.repository;

import com.ltp.furniture_store.entity.Status;
import com.ltp.furniture_store.entity.OrderStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Short> {
    Status findByDescriptionStatus(OrderStatusEnum orderStatusEnum);
}

