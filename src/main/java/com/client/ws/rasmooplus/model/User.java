package com.client.ws.rasmooplus.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
            name = "users_id"
    )
    private  Long id;

    private String name;

    private String email;

    private String phone;

    private String cpf;


    @Column(name = "dt_subscription"
    )
    private LocalDate dtSubscription = LocalDate.now();

    @Column(
            name = "dt_expiration"
    )
    private  LocalDate dtExpired;

    @ManyToOne
    @JoinColumn(name = "user_type_id")
    private  UserType userType;


    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "subscriptions_type_id"
    )
    private SubscriptionType subscriptionType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDtSubscription() {
        return dtSubscription;
    }

    public void setDtSubscription(LocalDate dtSubscription) {
        this.dtSubscription = dtSubscription;
    }

    public LocalDate getDtExpired() {
        return dtExpired;
    }

    public void setDtExpired(LocalDate dtExpired) {
        this.dtExpired = dtExpired;
    }

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(SubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
