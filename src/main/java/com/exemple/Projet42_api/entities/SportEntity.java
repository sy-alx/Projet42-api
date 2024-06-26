package com.exemple.Projet42_api.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.List;
import java.util.Set;

@Entity
public class SportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "sports")
    @JsonBackReference
    private Set<EventEntity> events;


    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<EventEntity> getEvents() {
        return (Set<EventEntity>) events;
    }

    public void setEvents(Set<EventEntity> events) {
        this.events = (Set<EventEntity>) events;
    }
}