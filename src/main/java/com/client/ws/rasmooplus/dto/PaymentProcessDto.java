package com.client.ws.rasmooplus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentProcessDto {


    @NotBlank(message = "Deve ser informado um valor")
    private String productKey;

    private BigDecimal discount;

    @NotNull(message = "Dados do pagamento deve ser informado")
    @JsonProperty( "userPaymentInfo")
    private UserPaymentInfoDto userPaymentInfoDto;
}
