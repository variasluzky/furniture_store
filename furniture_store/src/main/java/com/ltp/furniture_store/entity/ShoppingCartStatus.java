package com.ltp.furniture_store.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shopping_cart_status")
public class ShoppingCartStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_cart")
    private Short statusCart;

    @NonNull
    @Enumerated(EnumType.STRING) // This annotation maps the enum to a string in the database
    @Column(name = "status_description", nullable = false)
    private ShoppingCartStatusEnum statusDescription;
}