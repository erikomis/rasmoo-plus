package com.client.ws.rasmooplus.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_payment_info")
public class UserPaymentInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
            name = "user_payment_info_id"
    )
    private Long id;


    @Column(
            name = "card_number",
            unique = true
    )
    private String cardNumber;


    @Column(name = "card_expiration_month")
    private Long cardExpirationMonth;

    @Column(name = "card_expiration_year")
    private Long cardExpirationYear;
    @Column(name = "card_security_code")
    private String cardSecurityCode;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "dt_payment")
    private LocalDate dtPayment;

    @OneToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "user_id"
    )
    private User user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Long getCardExpirationMonth() {
        return cardExpirationMonth;
    }

    public void setCardExpirationMonth(Long cardExpirationMonth) {
        this.cardExpirationMonth = cardExpirationMonth;
    }

    public Long getCardExpirationYear() {
        return cardExpirationYear;
    }

    public void setCardExpirationYear(Long cardExpirationYear) {
        this.cardExpirationYear = cardExpirationYear;
    }

    public String getCardSecurityCode() {
        return cardSecurityCode;
    }

    public void setCardSecurityCode(String cardSecurityCode) {
        this.cardSecurityCode = cardSecurityCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getDtPayment() {
        return dtPayment;
    }

    public void setDtPayment(LocalDate dtPayment) {
        this.dtPayment = dtPayment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
