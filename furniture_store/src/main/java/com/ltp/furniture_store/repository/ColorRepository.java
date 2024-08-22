package com.ltp.furniture_store.repository;

import com.ltp.furniture_store.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository <Color, Short> {
    // Method to find color by its color description
    Color findBycolorDescription(String colorDescription);
}
