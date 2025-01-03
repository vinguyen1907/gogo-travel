package com.uit.se.gogo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    @ManyToOne
    @JsonIgnore
    private Stay stay;
    @JsonProperty("base_fare")
    private Double baseFare;
    private Double discount;
    private Double tax; // rate
    @JsonProperty("service_fee")
    private Double serviceFee;
    private String type;
    @JsonProperty("is_available")
    private Boolean isAvailable;
    @JsonProperty("max_guests")
    private Integer maxGuests;
    @JsonProperty("image_url")
    private String imageUrl;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;
}
