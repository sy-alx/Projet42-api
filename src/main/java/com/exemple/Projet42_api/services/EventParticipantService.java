package com.exemple.Projet42_api.services;

import com.exemple.Projet42_api.entities.EventEntity;
import com.exemple.Projet42_api.entities.EventParticipantEntity;
import com.exemple.Projet42_api.repositories.EventParticipantRepository;
import com.exemple.Projet42_api.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventParticipantService {

    @Autowired
    private EventParticipantRepository eventParticipantRepository;

    @Autowired
    private EventRepository eventRepository;

    public EventParticipantEntity registerToEvent(Long eventId, String participantId) {
        EventEntity event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        EventParticipantEntity eventParticipant = new EventParticipantEntity();
        eventParticipant.setParticipantId(String.valueOf(participantId));
        eventParticipant.setEvent(event);

        return eventParticipantRepository.save(eventParticipant);
    }

    public void unregisterFromEvent(Long eventId, String participantId) {
        EventParticipantEntity eventParticipant = eventParticipantRepository
                .findByEventIdAndParticipantId(eventId, participantId)
                .orElseThrow(() -> new RuntimeException("Registration not found"));

        eventParticipantRepository.delete(eventParticipant);
    }

    public boolean isRegisteredToEvent(Long eventId, String participantId) {
        return eventParticipantRepository.findByEventIdAndParticipantId(eventId, participantId).isPresent();
    }

}
