package com.ltp.furniture_store.repository;

import com.ltp.furniture_store.entity.ShoppingCartStatus;
import com.ltp.furniture_store.entity.ShoppingCartStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartStatusRepository extends JpaRepository<ShoppingCartStatus, Short> {
    ShoppingCartStatus findByStatusDescription(ShoppingCartStatusEnum statusDescription);
}