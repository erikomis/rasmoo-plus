package com.client.ws.rasmooplus.service;

import com.client.ws.rasmooplus.model.UserCredentials;

public interface UserDetailsService {


    UserCredentials loadUserByUsernameAndPass(String username, String pass);
//
//    void sendRecoveryCode(String email);
//
//    boolean recoveryCodeIsValid(String recoveryCode, String email);
//
//    void updatePasswordByRecoveryCode(UserDetailsDto userDetailsDto);
}
