package com.exemple.Projet42_api.repositories;

import com.exemple.Projet42_api.entities.EventParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventParticipantRepository extends JpaRepository<EventParticipantEntity, Long> {
    Optional<EventParticipantEntity> findByEventIdAndParticipantId(Long eventId, String participantId);
}
