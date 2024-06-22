package com.exemple.Projet42_api.repositories;

import com.exemple.Projet42_api.entities.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventEntity, Long> {
}
