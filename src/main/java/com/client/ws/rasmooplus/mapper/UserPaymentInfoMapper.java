package com.client.ws.rasmooplus.mapper;

import com.client.ws.rasmooplus.dto.UserPaymentInfoDto;
import com.client.ws.rasmooplus.model.jpa.User;
import com.client.ws.rasmooplus.model.jpa.UserPaymentInfo;

public class UserPaymentInfoMapper {


    public  static UserPaymentInfo fromDtoToEntity(UserPaymentInfoDto dto, User user){
        return  UserPaymentInfo.builder()
                .id(dto.getId())
                        .cardNumber(dto.getCardNumber())
                        .cardExpirationMonth(dto.getCardExpirationMonth())
                        .cardExpirationYear(dto.getCardExpirationYear())
                        .cardSecurityCode(dto.getCardSecurityCode())
                        .price(dto.getPrice())
                        .installments(dto.getInstallments())
                        .dtPayment(dto.getDtPayment())
                        .user(user)
                .build();

    }
}
