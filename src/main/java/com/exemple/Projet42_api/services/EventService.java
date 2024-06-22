package com.exemple.Projet42_api.services;

import com.exemple.Projet42_api.DTO.EventSummaryDto;
import com.exemple.Projet42_api.entities.EventEntity;
import com.exemple.Projet42_api.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;


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
}
