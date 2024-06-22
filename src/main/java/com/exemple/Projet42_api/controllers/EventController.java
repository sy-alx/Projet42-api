package com.exemple.Projet42_api.controllers;

import com.exemple.Projet42_api.DTO.EventSummaryDto;
import com.exemple.Projet42_api.entities.EventEntity;
import com.exemple.Projet42_api.services.EventService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;



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
}
