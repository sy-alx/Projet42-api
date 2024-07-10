package com.exemple.Projet42_api.config;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class KeycloakConfig {

    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl("http://10.43.250.30:9090")
                .realm("projet42-realm")
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId("admin-cli")
                .clientSecret("mItdGE3YNmGpg0TTIXp7qfglA9VQMFuO")
                .username("admin")
                .password("admin")
                .build();
    }
}


