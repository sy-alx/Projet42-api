package com.exemple.Projet42_api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.oauth2.jwt.Jwt;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping
    @Operation(summary = "Récupérer un information de l'utilisateur connecté (token)")
    public Map<String, Object> getUserInfo(@AuthenticationPrincipal Jwt jwt) {
        return Map.of(
                "name", jwt.getClaim("name"),
                "email", jwt.getClaim("email"),
                "given_name", jwt.getClaim("given_name"),
                "family_name", jwt.getClaim("family_name")
        );
    }
}




