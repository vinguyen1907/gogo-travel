package com.uit.se.gogo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceAmenity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    private Amenity amenity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    private BaseService service;
}
