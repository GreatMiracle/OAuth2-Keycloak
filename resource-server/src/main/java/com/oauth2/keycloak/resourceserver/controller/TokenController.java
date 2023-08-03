package com.oauth2.keycloak.resourceserver.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/token")
public class TokenController {
    @GetMapping
    public Object getToken (){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()){
            Jwt token= (Jwt) authentication.getCredentials();
            String accessTokenValue = token.getTokenValue();
            String principalName = token.getSubject();
            return Collections.singletonMap("principal", token);
        }else {
            return "Chưa xác thực";
        }
    }


    @GetMapping("/jwt")
    public Jwt getToken(@AuthenticationPrincipal Jwt jwt) {

        return jwt;
    }
}
