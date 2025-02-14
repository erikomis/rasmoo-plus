package com.client.ws.rasmooplus.controller;

import com.client.ws.rasmooplus.dto.LoginDto;
import com.client.ws.rasmooplus.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {


    @Autowired
    private AuthenticationService  authenticationService;

    @PostMapping
    public ResponseEntity<?> auth(@Valid  @RequestBody LoginDto loginDto) {
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.auth(loginDto));

    }

}
