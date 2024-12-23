package com.uit.se.gogo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String city;
    private String country;
    private String description;
    private String imageUrl;
}
