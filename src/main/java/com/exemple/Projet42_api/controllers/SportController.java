package com.exemple.Projet42_api.controllers;

import com.exemple.Projet42_api.entities.SportEntity;
import com.exemple.Projet42_api.services.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sports")
public class SportController {

    @Autowired
    private SportService sportService;

    @GetMapping
    public List<SportEntity> getAllSports() {
        return sportService.getAllSports();
    }

    @GetMapping("/{id}")
    public SportEntity getSportById(@PathVariable Long id) {
        return sportService.getSportById(id);
    }

    @PostMapping
    public SportEntity createSport(@RequestBody SportEntity sport) {
        return sportService.createSport(sport);
    }

    @DeleteMapping("/{id}")
    public void deleteSport(@PathVariable Long id) {
        sportService.deleteSport(id);
    }
}
