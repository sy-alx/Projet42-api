package com.exemple.Projet42_api.controllers;

import com.exemple.Projet42_api.DTO.EventSummaryDto;
import com.exemple.Projet42_api.entities.EventEntity;
import com.exemple.Projet42_api.entities.EventParticipantEntity;
import com.exemple.Projet42_api.services.EventParticipantService;
import com.exemple.Projet42_api.services.EventService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventParticipantService eventParticipantService;


    @PostMapping
    @Operation(summary = "Add an event")
    public EventEntity createEvent(@RequestBody EventEntity event) {
        return eventService.createEvent(event);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an event")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }

    @GetMapping("/eventsSummarize")
    @Operation(summary = "View a summary list of available events")
    public List<EventSummaryDto> getAllEventSummaries() {
        return eventService.getAllEventSummaries();
    }

    @GetMapping("/eventDetails/{id}")
    @Operation(summary = "Get detailed information of an event by Id")
    public EventEntity getEventDetailsById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

    @PostMapping("/{eventId}/register")
    @Operation(summary = "Register to an event")
    public EventParticipantEntity registerToEvent(@PathVariable Long eventId, @AuthenticationPrincipal Jwt jwt) {
        String participantId = jwt.getSubject(); // Now it's a String
        return eventParticipantService.registerToEvent(eventId, participantId);
    }

    @DeleteMapping("/{eventId}/unregister")
    @Operation(summary = "Unregister from an event")
    public void unregisterFromEvent(@PathVariable Long eventId, @AuthenticationPrincipal Jwt jwt) {
        String participantId = jwt.getSubject();
        eventParticipantService.unregisterFromEvent(eventId, participantId);
    }

    @GetMapping("/{eventId}/isRegistered")
    @Operation(summary = "Check if user is registered to an event")
    public boolean isRegisteredToEvent(@PathVariable Long eventId, @AuthenticationPrincipal Jwt jwt) {
        String participantId = jwt.getSubject();
        return eventParticipantService.isRegisteredToEvent(eventId, participantId);
    }
}
