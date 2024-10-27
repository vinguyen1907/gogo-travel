package com.uit.se.gogo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.se.gogo.enums.ReviewServiceType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_id")
    private BaseService service;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    private Integer rating;
    private String description;
    @JsonProperty("service_type")
    @Enumerated(EnumType.STRING)
    private ReviewServiceType serviceType;
}
