package com.ltp.furniture_store.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;



@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "registered_customers")
public class RegisteredCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id",nullable = false)
    private Integer customerId;

    @NonNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NonNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NonNull
    @Column(name = "phone", nullable = false)
    private String phone;

    @NonNull
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "permission_type_id")
    private PermissionType permissions;

    @NonNull
    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @NonNull
    @Column(name = "created_at",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @NonNull
    @Column(name = "updated_at",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date updatedAt;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    // Constructor to automatically set creation and update dates and default permissions
    public RegisteredCustomer(String firstName, String lastName, String phone, String email, String password, PermissionType permissions) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.permissions = permissions;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    // Constructor for fields received from registration form
    public RegisteredCustomer(String firstName, String lastName, String email, String password, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.createdAt = new Date(); // Assuming you want to set creation and update dates at the time of registration
        this.updatedAt = new Date();
    }

    @Override
    public String toString() {
        return "RegisteredCustomer{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", permissions=" + (permissions != null ? permissions.getPermissionTypeName() : "null") +
                ", password='" + password + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", orders=" + (orders != null ? orders.size() + " orders" : "no orders") +
                '}';
    }

}
