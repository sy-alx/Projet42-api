package com.exemple.Projet42_api.controllers;

import com.exemple.Projet42_api.DTO.EventDateUpdateRequest;
import com.exemple.Projet42_api.DTO.EventStatusUpdateRequest;
import com.exemple.Projet42_api.DTO.EventSummaryDto;
import com.exemple.Projet42_api.DTO.EventTimeUpdateRequest;
import com.exemple.Projet42_api.entities.EventEntity;
import com.exemple.Projet42_api.entities.EventParticipantEntity;
import com.exemple.Projet42_api.services.EventParticipantService;
import com.exemple.Projet42_api.services.EventService;
import com.exemple.Projet42_api.services.FileStorageService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventParticipantService eventParticipantService;

    @Autowired
    private FileStorageService fileStorageService;


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
    @Operation(summary = "Register to an event with a PDF file")
    public ResponseEntity<String> registerToEvent(
            @PathVariable Long eventId,
            @RequestPart("file") MultipartFile file,
            @AuthenticationPrincipal Jwt jwt) throws IOException {

        String participantId = jwt.getSubject();
        String userGivenName = jwt.getClaimAsString("given_name");
        String userFamilyName = jwt.getClaimAsString("family_name");

        // Renommage et stockage du fichier
        String fileName = userFamilyName + "_" + userGivenName + "_" + eventId + ".pdf";
        fileStorageService.store(file, fileName);

        // Inscription à l'évènement
        EventParticipantEntity participant = eventParticipantService.registerToEvent(eventId, participantId);
        return ResponseEntity.ok("Registered to event with file: " + fileName);
    }

    @DeleteMapping("/{eventId}/unregister")
    @Operation(summary = "Unregister from an event")
    public void unregisterFromEvent(@PathVariable Long eventId, @AuthenticationPrincipal Jwt jwt) {
        String participantId = jwt.getSubject();
        String userGivenName = jwt.getClaimAsString("given_name");
        String userFamilyName = jwt.getClaimAsString("family_name");
        eventParticipantService.unregisterFromEvent(eventId, participantId, userGivenName, userFamilyName);
    }

    @GetMapping("/{eventId}/isRegistered")
    @Operation(summary = "Check if user is registered to an event")
    public boolean isRegisteredToEvent(@PathVariable Long eventId, @AuthenticationPrincipal Jwt jwt) {
        String participantId = jwt.getSubject();
        return eventParticipantService.isRegisteredToEvent(eventId, participantId);
    }

    @PatchMapping("/{eventId}/updateEventDate")
    @Operation(summary = "Update the event date")
    public EventEntity updateEventDate(
            @PathVariable Long eventId,
            @Valid @RequestBody EventDateUpdateRequest updateRequest) {
        return eventService.updateEventDate(eventId, updateRequest.getEventDate());
    }

    @PatchMapping("/{eventId}/updateEventTime")
    @Operation(summary = "Update the event time")
    public EventEntity updateEventTime(
            @PathVariable Long eventId,
            @Valid @RequestBody EventTimeUpdateRequest updateRequest) {
        return eventService.updateEventTime(eventId, updateRequest.getEventTime());
    }

    @PatchMapping("/{eventId}/updateEventStatus")
    @Operation(summary = "Update the event status")
    public EventEntity updateEventStatus(
            @PathVariable Long eventId,
            @Valid @RequestBody EventStatusUpdateRequest updateRequest) {
        return eventService.updateEventStatus(eventId, updateRequest.getStatusId());
    }
}
