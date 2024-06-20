package com.exemple.Projet42_api.services;

import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class KeycloakService {

    private static final Logger logger = LoggerFactory.getLogger(KeycloakService.class);

    private final Keycloak keycloak;

    public KeycloakService(Keycloak keycloak) {
        this.keycloak = keycloak;
    }

    public boolean updateUserEmail(String userId, String newEmail) {
        try {
            UserRepresentation user = keycloak.realm("projet42-realm").users().get(userId).toRepresentation();
            user.setEmail(newEmail);
            keycloak.realm("projet42-realm").users().get(userId).update(user);
            return true;
        } catch (Exception e) {
            logger.error("Failed to update email", e);
            return false;
        }
    }

    public boolean updateUserPassword(String userId, String newPassword) {
        try {
            CredentialRepresentation credential = new CredentialRepresentation();
            credential.setType(CredentialRepresentation.PASSWORD);
            credential.setValue(newPassword);
            credential.setTemporary(false);

            keycloak.realm("projet42-realm").users().get(userId).resetPassword(credential);
            return true;
        } catch (Exception e) {
            logger.error("Failed to update password", e);
            return false;
        }
    }

    public boolean logoutUser(String refreshToken) {
        String logoutEndpoint = "/realms/projet42-realm/protocol/openid-connect/logout";

        try {
            WebClient.create("http://localhost:8090")
                    .post()
                    .uri(logoutEndpoint)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .bodyValue("client_id=admin-cli&client_secret=mItdGE3YNmGpg0TTIXp7qfglA9VQMFuO&refresh_token=" + refreshToken)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            return true;
        } catch (Exception e) {
            logger.error("Failed to logout user", e);
            return false;
        }
    }


    public boolean createUser(String username, String firstName, String lastName, String email, String password) {
        try {
            UserRepresentation user = new UserRepresentation();
            user.setUsername(username);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setEnabled(true);
            user.setEmailVerified(true);

            Response response = keycloak.realm("projet42-realm").users().create(user);

            if (response.getStatus() != 201) {
                throw new RuntimeException("Failed to create user");
            }

            String userId = CreatedResponseUtil.getCreatedId(response);

            CredentialRepresentation credential = new CredentialRepresentation();
            credential.setType(CredentialRepresentation.PASSWORD);
            credential.setValue(password);
            credential.setTemporary(false);

            keycloak.realm("projet42-realm").users().get(userId).resetPassword(credential);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
