package com.exemple.Projet42_api.services;

import com.exemple.Projet42_api.DTO.EventSummaryDto;
import com.exemple.Projet42_api.entities.EventEntity;
import com.exemple.Projet42_api.entities.StatusEntity;
import com.exemple.Projet42_api.repositories.EventRepository;
import com.exemple.Projet42_api.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private StatusRepository statusRepository;


    public EventEntity getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    public EventEntity createEvent(EventEntity event) {
        return eventRepository.save(event);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    public List<EventSummaryDto> getAllEventSummaries() {
        return eventRepository.findAll().stream().map(event -> {
            EventSummaryDto dto = new EventSummaryDto();
            dto.setId(event.getId());
            dto.setName(event.getName());
            dto.setAddress(event.getAddress());
            dto.setStatus(event.getStatus().getName());
            dto.setEventDate(event.getEventDate());
            dto.setEventTime(event.getEventTime());
            return dto;
        }).collect(Collectors.toList());
    }

    public EventEntity updateEventDate(Long eventId, LocalDate eventDate) {
        EventEntity event = eventRepository.findById(eventId).orElseThrow(() -> new IllegalArgumentException("Event not found"));
        event.setEventDate(eventDate);
        return eventRepository.save(event);
    }

    public EventEntity updateEventTime(Long eventId, LocalTime eventTime) {
        EventEntity event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
        event.setEventTime(eventTime);
        return eventRepository.save(event);
    }

    public EventEntity updateEventStatus(Long eventId, Long statusId) {
        EventEntity event = eventRepository.findById(eventId).orElseThrow(() -> new IllegalArgumentException("Event not found"));
        StatusEntity status = statusRepository.findById(statusId).orElseThrow(() -> new IllegalArgumentException("Status not found"));
        event.setStatus(status);
        return eventRepository.save(event);
    }
}
