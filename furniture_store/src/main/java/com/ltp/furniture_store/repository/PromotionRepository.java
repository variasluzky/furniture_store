package com.ltp.furniture_store.repository;

import com.ltp.furniture_store.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<Promotion,Short> {
    //method to find promotion by description
    Promotion findByDescription(String promotionDescription);
}
