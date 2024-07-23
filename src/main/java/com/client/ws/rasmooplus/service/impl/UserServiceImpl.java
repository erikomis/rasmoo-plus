package com.client.ws.rasmooplus.service.impl;

import com.client.ws.rasmooplus.dto.UserDto;
import com.client.ws.rasmooplus.exception.BadRequestException;
import com.client.ws.rasmooplus.exception.NotFoudException;
import com.client.ws.rasmooplus.mapper.UserMapper;
import com.client.ws.rasmooplus.model.User;
import com.client.ws.rasmooplus.model.UserType;
import com.client.ws.rasmooplus.repository.UserRepository;
import com.client.ws.rasmooplus.repository.UserTypeRepository;
import com.client.ws.rasmooplus.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;

    public UserServiceImpl(UserRepository userRepository, UserTypeRepository userTypeRepository) {
        this.userRepository = userRepository;
        this.userTypeRepository = userTypeRepository;
    }


    @Override
    public User createUser(UserDto user) {
        if (Objects.nonNull(user.getId())) {
            throw new BadRequestException("O id deve ser nulo");
        }

        var userTypeOpt = userTypeRepository.findById(user.getUserTypeId());

        if (userTypeOpt.isEmpty()) {
            throw new NotFoudException("userTypeId nao encontrado");
        }

        UserType userType = userTypeOpt.get();
        User userM = UserMapper.fromDtoToEntity(user, userType, null);


        return userRepository.save(userM);
    }
}
