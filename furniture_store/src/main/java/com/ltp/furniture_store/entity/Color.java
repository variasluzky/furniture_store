package com.ltp.furniture_store.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "color")
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "color_code")
    private Short colorCode;

    @NonNull
    @Column(name = "color_description", nullable = false)
    private String colorDescription;

}
