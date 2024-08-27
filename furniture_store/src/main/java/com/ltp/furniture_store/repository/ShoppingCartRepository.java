package com.ltp.furniture_store.repository;

import com.ltp.furniture_store.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
    Optional<ShoppingCart> findByCustomerAndShoppingCartStatus_StatusDescription(RegisteredCustomer customer, ShoppingCartStatusEnum status);
}

