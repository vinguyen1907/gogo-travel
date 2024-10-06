package com.uit.se.gogo.entity;

import com.uit.se.gogo.enums.AmenityIcon;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Amenity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    @Enumerated(EnumType.STRING)
    private AmenityIcon icon;
    private Boolean isFeature;
}
