package com.exemple.Projet42_api.DTO;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class EventDateUpdateRequest {

    @NotNull
    private LocalDate eventDate;

    // Getters and setters
    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }
}
