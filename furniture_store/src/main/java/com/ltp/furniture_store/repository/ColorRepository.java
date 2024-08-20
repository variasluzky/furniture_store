package com.ltp.furniture_store.repository;

import com.ltp.furniture_store.entity.Color;
import com.ltp.furniture_store.entity.PermissionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository <Color, Short> {
    // Method to find color by its color description
    PermissionType findBycolorDescription(String colorDescription);
}
