package com.ltp.furniture_store.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "promotion")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promotion_code")
    private Short promotionCode;

    @NonNull
    @Column(name = "discount", nullable = false)
    private BigDecimal discount;  // This will store the percentage discount

    @NonNull
    @Column(name = "description", nullable = false, length = 255)
    private String description;  // This will store the description of the promotion

}
