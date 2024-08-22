package com.ltp.furniture_store.repository;

import com.ltp.furniture_store.entity.Catalog;
import com.ltp.furniture_store.entity.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface CatalogRepository extends JpaRepository<Catalog,Long> {
    List<Catalog> findByProductName(String productName);
    List<Catalog> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
    List<Catalog> findByTypeOfItem(ItemType typeOfItem);

}
