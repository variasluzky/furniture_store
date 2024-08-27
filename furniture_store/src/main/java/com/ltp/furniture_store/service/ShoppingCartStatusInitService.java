package com.ltp.furniture_store.service;

import com.ltp.furniture_store.entity.ShoppingCartStatus;
import com.ltp.furniture_store.entity.ShoppingCartStatusEnum;
import com.ltp.furniture_store.repository.ShoppingCartStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class ShoppingCartStatusInitService {

    @Autowired
    private ShoppingCartStatusRepository shoppingCartStatusRepository;

    @PostConstruct
    public void init() {
        for (ShoppingCartStatusEnum statusEnum : ShoppingCartStatusEnum.values()) {
            ShoppingCartStatus status = shoppingCartStatusRepository.findByStatusDescription(statusEnum);
            if (status == null) {
                status = new ShoppingCartStatus();
                status.setStatusDescription(statusEnum);
                shoppingCartStatusRepository.save(status);
            }
        }
    }
}
