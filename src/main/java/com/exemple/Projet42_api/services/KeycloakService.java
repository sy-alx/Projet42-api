package com.exemple.Projet42_api.services;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.KeycloakBuilder;

@Service
public class KeycloakService {

    private final String serverUrl;
    private final String realm;
    private final String clientId;

    public KeycloakService(
            @Value("${keycloak.auth-server-url}") String serverUrl,
            @Value("${keycloak.realm}") String realm,
            @Value("${keycloak.client.id}") String clientId) {
        this.serverUrl = serverUrl;
        this.realm = realm;
        this.clientId = clientId;
    }

    public boolean updateUserEmail(String userId, String newEmail, String userAccessToken) {
        try {
            Keycloak keycloak = KeycloakBuilder.builder()
                    .serverUrl(serverUrl)
                    .realm(realm)
                    .authorization(userAccessToken)
                    .clientId(clientId)
                    .build();

            UserRepresentation user = keycloak.realm(realm).users().get(userId).toRepresentation();
            user.setEmail(newEmail);
            keycloak.realm(realm).users().get(userId).update(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUserPassword(String userId, String newPassword, String userAccessToken) {
        try {
            Keycloak keycloak = KeycloakBuilder.builder()
                    .serverUrl(serverUrl)
                    .realm(realm)
                    .authorization(userAccessToken)
                    .clientId(clientId)
                    .build();

            CredentialRepresentation credential = new CredentialRepresentation();
            credential.setType(CredentialRepresentation.PASSWORD);
            credential.setValue(newPassword);
            credential.setTemporary(false);

            keycloak.realm(realm).users().get(userId).resetPassword(credential);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
