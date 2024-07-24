package com.client.ws.rasmooplus.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private  Long id;

    @NotBlank(message = "Valor não pode ser nulo ou vazio")
    @Size(min = 6, message = "Valor minimo igual a 6 caracteres")
    private String name;

    @NotBlank(message = "Valor não pode ser nulo ou vazio")
    @Email(message = "invalido")
    private String email;

    @Size(min = 11, message = "Valor minimo igual a 11 digitos")
    private String phone;

    @NotBlank(message = "Valor não pode ser nulo ou vazio")
    @CPF(message = "Invalido")
    private String cpf;

    private LocalDate dtSubscription = LocalDate.now();


    private  LocalDate dtExpired = LocalDate.now();

    @NotNull
    private Long userTypeId;

    private Long subscriptionTypeId;
}
