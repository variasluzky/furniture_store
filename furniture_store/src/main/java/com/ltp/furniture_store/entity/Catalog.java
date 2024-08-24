package com.ltp.furniture_store.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "catalog")
public class Catalog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productID;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(name="type_of_item",nullable = false)
    private ItemType typeOfItem;

    @NonNull
    @Column(name = "product_name", nullable = false,length = 100)
    private String productName;

    @NonNull
    @Column(name = "description",length = 250)
    private String description;

    @NonNull
    @Lob
    @Column(name = "description_full")
    private String descriptionFull;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "color_code", nullable = false)
    private Color color;

    @NonNull
    @Column(name = "price",nullable = false)
    @DecimalMin(value = "0.01", message = "Price must be greater than zero" )
    private BigDecimal price;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "promotion_code",nullable = false)
    private Promotion promotion;

    @NonNull
    @Column(name = "stock",nullable = false)
    @Min(value = 0,message = "Stock can't be negative number")
    private Integer stock;

    @NonNull
    @Column(name = "status",nullable = false)
    private Boolean status;
}
