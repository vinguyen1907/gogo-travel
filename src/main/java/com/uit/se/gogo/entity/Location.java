package com.uit.se.gogo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Location {
    @Id
    @GeneratedValue
    private String city;
    private String country;
    private String description;
}
