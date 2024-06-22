package com.exemple.Projet42_api.services;

import com.exemple.Projet42_api.entities.SportEntity;
import com.exemple.Projet42_api.repositories.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportService {

    @Autowired
    private SportRepository sportRepository;

    public List<SportEntity> getAllSports() {
        return sportRepository.findAll();
    }

    public SportEntity getSportById(Long id) {
        return sportRepository.findById(id).orElse(null);
    }

    public SportEntity createSport(SportEntity sport) {
        return sportRepository.save(sport);
    }

    public void deleteSport(Long id) {
        sportRepository.deleteById(id);
    }
}
