package com.exemple.Projet42_api.repositories;

import com.exemple.Projet42_api.entities.SportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportRepository extends JpaRepository<SportEntity, Long> {
}
