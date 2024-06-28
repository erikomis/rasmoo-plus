package com.client.ws.rasmooplus.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionTypeDto {

    private Long id;
    private String name;
    private Long accessMonth;
    private BigDecimal price;
    private String  productKey;


}
