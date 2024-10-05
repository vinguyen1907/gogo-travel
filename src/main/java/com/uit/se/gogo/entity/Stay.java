package com.uit.se.gogo.entity;

import com.uit.se.gogo.enums.StayType;
import jakarta.persistence.*;

@Entity()
public class Stay {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String name;
    private String address;
    @ManyToOne
    private Location location;
    private Double rating;
    private Integer starRating;
    @Enumerated
    private StayType stayType;
    private String overview;
    private Double latitude;
    private Double longitude;
}
