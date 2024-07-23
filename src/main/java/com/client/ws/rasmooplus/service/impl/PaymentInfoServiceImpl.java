package com.client.ws.rasmooplus.service.impl;

import com.client.ws.rasmooplus.dto.PaymentProcessDto;
import com.client.ws.rasmooplus.dto.wsraspay.CustomerDto;
import com.client.ws.rasmooplus.dto.wsraspay.OrderDto;
import com.client.ws.rasmooplus.dto.wsraspay.PaymentDto;
import com.client.ws.rasmooplus.enums.UserTypeEnum;
import com.client.ws.rasmooplus.exception.BusinessExcepion;
import com.client.ws.rasmooplus.exception.NotFoudException;
import com.client.ws.rasmooplus.integration.MailIntegration;
import com.client.ws.rasmooplus.integration.WsRaspayIntegration;
import com.client.ws.rasmooplus.mapper.UserPaymentInfoMapper;
import com.client.ws.rasmooplus.mapper.wsraspay.CreditCardMapper;
import com.client.ws.rasmooplus.mapper.wsraspay.CustomerMapper;
import com.client.ws.rasmooplus.mapper.wsraspay.OrderMapper;
import com.client.ws.rasmooplus.mapper.wsraspay.PaymentMapper;
import com.client.ws.rasmooplus.model.User;
import com.client.ws.rasmooplus.model.UserCredentials;
import com.client.ws.rasmooplus.model.UserPaymentInfo;
import com.client.ws.rasmooplus.model.UserType;
import com.client.ws.rasmooplus.repository.*;
import com.client.ws.rasmooplus.service.PaymentInfoService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PaymentInfoServiceImpl  implements PaymentInfoService {




    private final UserRepository userRepository;
    private final UserPaymentInfoRepository userPaymentInfoRepository;
    private final WsRaspayIntegration wsRaspayIntegration;
    private final MailIntegration mailIntegration;
    private final UserDetailsRepository userDetailsRepository;
    private final UserTypeRepository userTypeRepository;
    private final SubscriptionTypeRepository subscriptionTypeRepository;

    PaymentInfoServiceImpl(UserRepository userRepository, UserPaymentInfoRepository userPaymentInfoRepository,
                           WsRaspayIntegration wsRaspayIntegration, MailIntegration mailIntegration,
                           UserDetailsRepository userDetailsRepository,
                           UserTypeRepository userTypeRepository,
                           SubscriptionTypeRepository subscriptionTypeRepository

    ) {
        this.userRepository = userRepository;
        this.userPaymentInfoRepository = userPaymentInfoRepository;
        this.wsRaspayIntegration = wsRaspayIntegration;
        this.mailIntegration = mailIntegration;
        this.userDetailsRepository = userDetailsRepository;
        this.userTypeRepository = userTypeRepository;
        this.subscriptionTypeRepository = subscriptionTypeRepository;
    }



    @Override
    public Boolean process(PaymentProcessDto dto) {

        var userOpt = userRepository.findById(dto.getUserPaymentInfoDto().getUserId());

        if (userOpt.isEmpty()) {
            throw new NotFoudException("User not found");
        }

        User user = userOpt.get();

        if (Objects.nonNull(user.getSubscriptionType())) {
            throw  new BusinessExcepion("Payment already processed");
        }

        Boolean sucessPayment = getSucessPayment(dto, user);

        return createUserCredentials(dto, sucessPayment, user);



    }

    private boolean createUserCredentials(PaymentProcessDto dto, Boolean sucessPayment, User user) {
        if (Boolean.TRUE.equals(sucessPayment)) {
            UserPaymentInfo userPaymentInfo = UserPaymentInfoMapper.fromDtoToEntity(dto.getUserPaymentInfoDto(), user);
            userPaymentInfoRepository.save(userPaymentInfo);

            var userTypeOpt = userTypeRepository.findById(UserTypeEnum.ALUNO.getId());


            if (userTypeOpt.isEmpty()) {
                throw new NotFoudException("userTypeId nao encontrado");
            }

            UserCredentials userCredentials = new UserCredentials(
                    null,
                    user.getEmail(),
                    new BCryptPasswordEncoder().encode("alunorasmoo"),
                    userTypeOpt.get()
            );

            userDetailsRepository.save(userCredentials);


            var subscriptionTypeOpt = subscriptionTypeRepository.findByProductKey(dto.getProductKey());


            if (subscriptionTypeOpt.isEmpty()) {
                throw new NotFoudException("subscriptionType nao encontrado");
            }

            user.setSubscriptionType(subscriptionTypeOpt.get());

            userRepository.save(user);


            mailIntegration.send(user.getEmail(),"Usuario: "+ user.getEmail()+" - Senha: alunorasmoo","Acesso liberado");
            return true;
        }
        return false;
    }

    private Boolean getSucessPayment(PaymentProcessDto dto, User user) {
        CustomerDto customerDto=  wsRaspayIntegration.createCustomer(CustomerMapper.build(user));
        OrderDto orderDto = wsRaspayIntegration.createOrder(OrderMapper.build(customerDto.getId(), dto));
        PaymentDto paymentDto =  PaymentMapper.build(customerDto.getId(), orderDto.getId(), CreditCardMapper.build(dto.getUserPaymentInfoDto(), user.getCpf()));
        Boolean sucessPayment = wsRaspayIntegration.processPayment(paymentDto);
        return sucessPayment;
    }
}
