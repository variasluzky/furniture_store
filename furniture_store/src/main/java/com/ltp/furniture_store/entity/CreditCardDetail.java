package com.ltp.furniture_store.entity;

import com.ltp.furniture_store.service.EncryptionService;
import jakarta.persistence.*;
import lombok.*;

import java.time.YearMonth;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "credit_card_detail")
public class CreditCardDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    private RegisteredCustomer registeredCustomer;

    @NonNull
    @Column(name = "cardholder_id", nullable = false)
    private String encryptedCardholderId;

    @NonNull
    @Column(name = "credit_card_number", nullable = false)
    private String encryptedCreditCardNumber;

    @NonNull
    @Column(name = "cardholder_first_name", nullable = false)
    private String cardholderFirstName;

    @NonNull
    @Column(name = "cardholder_last_name")
    private String cardholderLastName;

    @NonNull
    @Column(name = "credit_card_exp_date", nullable = false)
    private YearMonth creditCardExpDate;

    @Column(name = "cvv", nullable = false)
    private String encryptedCvv;


    // Custom constructor with encryption
    public CreditCardDetail(RegisteredCustomer registeredCustomer, String cardholderId, String creditCardNumber,
                            String cardholderFirstName, String cardholderLastName, YearMonth creditCardExpDate, String cvv,
                            EncryptionService encryptionService) {
        this.registeredCustomer = registeredCustomer;
        this.cardholderFirstName = cardholderFirstName;
        this.cardholderLastName = cardholderLastName;
        this.creditCardExpDate = creditCardExpDate;

        // Encrypt sensitive data
        this.encryptedCardholderId = encryptionService.encrypt(cardholderId);
        this.encryptedCreditCardNumber = encryptionService.encrypt(creditCardNumber);
        this.encryptedCvv = encryptionService.encrypt(cvv);
    }

    // Setters and getters for encrypted fields using EncryptionService

    public void setCvv(String cvv, EncryptionService encryptionService) {
        this.encryptedCvv = encryptionService.encrypt(cvv);
    }

    public String getCvv(EncryptionService encryptionService) {
        return encryptionService.decrypt(this.encryptedCvv);
    }

    public void setCardholderId(String cardholderId, EncryptionService encryptionService) {
        this.encryptedCardholderId = encryptionService.encrypt(cardholderId);
    }

    public String getCardholderId(EncryptionService encryptionService) {
        return encryptionService.decrypt(this.encryptedCardholderId);
    }

    public void setCreditCardNumber(String creditCardNumber, EncryptionService encryptionService) {
        this.encryptedCreditCardNumber = encryptionService.encrypt(creditCardNumber);
    }

    public String getCreditCardNumber(EncryptionService encryptionService) {
        return encryptionService.decrypt(this.encryptedCreditCardNumber);
    }
}
