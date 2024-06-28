package com.client.ws.rasmooplus.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionTypeDto {

    private Long id;

    @NotBlank(message = "O campo name não pode ser nulo ou vazio")
    private String name;
    @Max(value = 12, message = "campo accessMonth não pode ser maior que 12")
    private Long accessMonth;
    @NotNull(message = "O campo price não pode ser nulo")
    private BigDecimal price;

    @NotBlank(message = "O campo productKey não pode ser nulo ou vazio")
    @Size( min = 5, max=12 , message = "O campo productKey precisa ter entre 5 a 15 caracteres" )
    private String  productKey;


}
