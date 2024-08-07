package com.ltp.furniture_store.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "receipt_num")
    private Long receiptNum;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    private RegisteredCustomer customer;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id",nullable = false)
    private Order order;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="payment_id",nullable = false)
    private CreditCardDetail creditCardDetail;

    @NonNull
    @Column(name = "payment_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date paymentDate;


}
