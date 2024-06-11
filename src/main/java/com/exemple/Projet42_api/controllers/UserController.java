package com.exemple.Projet42_api.controllers;

import com.exemple.Projet42_api.DTO.EmailUpdateRequest;
import com.exemple.Projet42_api.DTO.PasswordUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import com.exemple.Projet42_api.services.KeycloakService;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final KeycloakService keycloakService;

    public UserController(KeycloakService keycloakService) {
        this.keycloakService = keycloakService;
    }

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

    @PutMapping("/email")
    @Operation(summary = "Mettre à jour l'adresse e-mail de l'utilisateur")
    public ResponseEntity<?> updateEmail(@AuthenticationPrincipal Jwt jwt,
                                         @RequestBody @Valid EmailUpdateRequest emailUpdateRequest) {
        String userId = jwt.getSubject();
        String newEmail = emailUpdateRequest.getNewEmail();
        String userAccessToken = jwt.getTokenValue();

        boolean isUpdated = keycloakService.updateUserEmail(userId, newEmail, userAccessToken);

        if (isUpdated) {
            return ResponseEntity.ok(Map.of("message", "Email changé avec succès"));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Email inchangé erreur rencontré"));
        }
    }

    @PutMapping("/password")
    @Operation(summary = "Mettre à jour le mot de passe de l'utilisateur")
    public ResponseEntity<?> updatePassword(@AuthenticationPrincipal Jwt jwt,
                                            @RequestBody @Valid PasswordUpdateRequest passwordUpdateRequest) {
        String userId = jwt.getSubject();
        String newPassword = passwordUpdateRequest.getNewPassword();
        String userAccessToken = jwt.getTokenValue();

        boolean isUpdated = keycloakService.updateUserPassword(userId, newPassword, userAccessToken);

        if (isUpdated) {
            return ResponseEntity.ok(Map.of("message", "Password changed successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Failed to change password"));
        }
    }
}
