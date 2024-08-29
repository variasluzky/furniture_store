package com.ltp.furniture_store.service;

import com.ltp.furniture_store.entity.OrderStatusEnum;
import com.ltp.furniture_store.entity.Status;
import com.ltp.furniture_store.repository.StatusRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusInitService {

    @Autowired
    private StatusRepository statusRepository;

    @PostConstruct
    public void init() {
        for (OrderStatusEnum orderStatusEnum : OrderStatusEnum.values()) {
            Status status = statusRepository.findByDescriptionStatus(orderStatusEnum);
            if (status == null) {
                status = new Status();
                status.setDescriptionStatus(orderStatusEnum);
                statusRepository.save(status);
            }
        }
    }
}
