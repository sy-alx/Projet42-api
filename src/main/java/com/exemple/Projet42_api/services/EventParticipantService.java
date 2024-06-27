package com.exemple.Projet42_api.services;

import com.exemple.Projet42_api.entities.EventEntity;
import com.exemple.Projet42_api.entities.EventParticipantEntity;
import com.exemple.Projet42_api.repositories.EventParticipantRepository;
import com.exemple.Projet42_api.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EventParticipantService {

    private static final Logger logger = LoggerFactory.getLogger(EventParticipantService.class);

    @Autowired
    private EventParticipantRepository eventParticipantRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private FileStorageService fileStorageService;

    public EventParticipantEntity registerToEvent(Long eventId, String participantId) {
        EventEntity event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        EventParticipantEntity eventParticipant = new EventParticipantEntity();
        eventParticipant.setParticipantId(participantId);
        eventParticipant.setEvent(event);

        return eventParticipantRepository.save(eventParticipant);
    }

    public void unregisterFromEvent(Long eventId, String participantId, String givenName, String familyName) {
        EventParticipantEntity eventParticipant = eventParticipantRepository
                .findByEventIdAndParticipantId(eventId, participantId)
                .orElseThrow(() -> new RuntimeException("Registration not found"));

        // Construct the file name based on user details and eventId
        String fileName = familyName + "_" + givenName + "_" + eventParticipant.getEvent().getId() + ".pdf";
        logger.info("Unregistering from event: " + eventId + " for participant: " + participantId);
        fileStorageService.delete(fileName);

        eventParticipantRepository.delete(eventParticipant);
    }

    public boolean isRegisteredToEvent(Long eventId, String participantId) {
        return eventParticipantRepository.findByEventIdAndParticipantId(eventId, participantId).isPresent();
    }
}
