package com.oauth2.keycloak.resourceserver.controller;

import com.oauth2.keycloak.resourceserver.dto.response.UserRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    Environment env;

    @GetMapping("/status/check")
    public String status() {
        return "Working on port: " + env.getProperty("local.server.port");
    }

    @Secured("ROLE_develop")
    @DeleteMapping("/secured/{userId}")
    public String deleteUsersecured(@PathVariable String userId) {
        return "Deleted user secured with ID: " + userId ;
    }

    @PreAuthorize("hasAuthority('ROLE_develop') or userId == jwt.subject")
    @DeleteMapping("/pre-authorize/{userId}")
    public String deleteUserPreAuthorize(@PathVariable String userId, @AuthenticationPrincipal Jwt jwt) {
        return "Deleted user pre-authorize with ID: " + userId + " and JWT subject " + jwt.getSubject();
    }

    @PostAuthorize("returnObject.body.userId == #jwt.subject")
    @GetMapping("/post-authorize/{userId}")
    public ResponseEntity<UserRest> getUserPostAuthorize(@PathVariable String userId, @AuthenticationPrincipal Jwt jwt) {
        return new ResponseEntity<>(new UserRest("OK", "iFegal","13c0eb7e-774d-4e3c-aea9-4050afe73e64"), HttpStatus.OK);
    }


}
