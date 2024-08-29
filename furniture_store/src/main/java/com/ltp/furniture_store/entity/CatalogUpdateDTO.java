package com.ltp.furniture_store.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CatalogUpdateDTO {

    private String productName;
    private String description;
    private String descriptionFull;
    private BigDecimal price;
    private Integer stock;
    private Boolean status;
    private ItemType typeOfItem;
    private Short colorCode;
    private Short promotionCode;

}
