package com.exemple.Projet42_api.DTO;

import jakarta.validation.constraints.NotNull;

public class EventStatusUpdateRequest {

    @NotNull
    private Long statusId;

    // Getters and setters
    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }
}
