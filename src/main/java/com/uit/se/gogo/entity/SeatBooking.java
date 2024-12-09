package com.uit.se.gogo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeatBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @OneToOne
    @JoinColumn(name = "seat_id", nullable=false)
    private Seat seat;

    @JsonProperty("citizen_id")
    private String citizenId;
    
    @JsonProperty("citizen_name")
    private String citizenName;
    
    @ManyToOne
    @JoinColumn(name = "booking_id", nullable=false)
    @JsonIgnoreProperties("seats")
    private FlightBooking booking;
}
