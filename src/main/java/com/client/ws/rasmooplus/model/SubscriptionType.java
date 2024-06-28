package com.client.ws.rasmooplus.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "subscriptions_type"
)
@Entity
public class SubscriptionType extends RepresentationModel<SubscriptionType> implements Serializable {


    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "subscriptions_type_id"
    )
    private Long id;


    private String name;

    @Column(
            name = "access_months"
    )
    private Long AccessMonth;

    private BigDecimal price;

    @Column(
            name = "product_key",
            unique = true
    )
    private String productKey;


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

    public Long getAccessMonth() {
        return AccessMonth;
    }

    public void setAccessMonth(Long accessMonth) {
        AccessMonth = accessMonth;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }
}
