package com.client.ws.rasmooplus.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @NotBlank(message = "Valor não pode ser nulo ou vazio")
    private String username;

    @NotBlank(message = "Valor não pode ser nulo ou vazio")
    @Size(min = 6, max = 16, message = "Senha deve ter no mínimo 6 caracteres")
    private String password;



}
