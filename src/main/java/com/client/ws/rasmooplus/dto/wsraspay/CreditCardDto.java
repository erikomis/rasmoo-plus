package com.client.ws.rasmooplus.dto.wsraspay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditCardDto {


    private Long cvv;

    private String documentNumber;

    private Long installments;

    private String number;

    private Long month;

    private Long year;
}
