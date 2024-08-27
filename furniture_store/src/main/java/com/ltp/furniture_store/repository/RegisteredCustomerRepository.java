package com.ltp.furniture_store.repository;
import com.ltp.furniture_store.entity.RegisteredCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
    public interface RegisteredCustomerRepository extends JpaRepository<RegisteredCustomer, Integer> {
       Optional <RegisteredCustomer> findByEmail(String email);
       Optional<RegisteredCustomer> findById(Integer customerId);
    }

