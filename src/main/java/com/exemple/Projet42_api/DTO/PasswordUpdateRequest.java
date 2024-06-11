package com.exemple.Projet42_api.DTO;

import jakarta.validation.constraints.NotBlank;

public class PasswordUpdateRequest {

    @NotBlank
    private String newPassword;

    // Getters and Setters

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
