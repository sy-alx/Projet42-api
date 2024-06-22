package com.exemple.Projet42_api.repositories;

import com.exemple.Projet42_api.entities.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<StatusEntity, Long> {
}
