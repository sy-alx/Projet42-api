package com.exemple.Projet42_api.repositories;

import com.exemple.Projet42_api.entities.EventParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventParticipantRepository extends JpaRepository<EventParticipantEntity, Long> {
}
