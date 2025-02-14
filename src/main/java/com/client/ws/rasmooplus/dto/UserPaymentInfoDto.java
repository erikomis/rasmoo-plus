package com.client.ws.rasmooplus.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPaymentInfoDto {

    private Long id;

    @Size(min = 16 , max = 16, message = "Deve conter 16 caracteres")
    private String cardNumber;

    @Min(value = 1)
    @Max(value = 12)
    private Long cardExpirationMonth;


    private Long cardExpirationYear;

    @Size(min = 3 , max = 3 , message = "Deve conter 3 caracteres")
    private String cardSecurityCode;

    private BigDecimal price;

    private LocalDate dtPayment = LocalDate.now();

    private Long installments;

    @NotNull(message = "Id deve ser informado ")
    private Long userId;
}
