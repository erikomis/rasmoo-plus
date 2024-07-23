package com.client.ws.rasmooplus.service.impl;

import com.client.ws.rasmooplus.exception.BadRequestException;
import com.client.ws.rasmooplus.exception.NotFoudException;
import com.client.ws.rasmooplus.model.UserCredentials;
import com.client.ws.rasmooplus.repository.UserDetailsRepository;
import com.client.ws.rasmooplus.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;


    @Override
    public UserCredentials loadUserByUsernameAndPass(String username, String pass) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        var userCredentialsOpt = userDetailsRepository.findByUsername(username);

        if (userCredentialsOpt.isEmpty()) {
            throw new NotFoudException("Usuário não encontrado");
        }

        UserCredentials userCredentials = userCredentialsOpt.get();

        if (encoder.matches(pass, userCredentials.getPassword())) {
            return userCredentials;
        }

        throw new BadRequestException("Usuário ou senha inválido");
    }
}
