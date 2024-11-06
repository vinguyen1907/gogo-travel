package com.uit.se.gogo.entity;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Flight extends BaseService {
    @ManyToOne
    @JoinColumn(name = "airline_id")
    private Airline airline;
    @ManyToOne
    @JoinColumn(name = "departure_airport_id", nullable = false)
    private Airport departureAirport;
    @ManyToOne
    @JoinColumn(name = "arrival_airport_id", nullable = false)
    private Airport arrivalAirport;

    private String gate;
    private String timezone;
    private Date departureTime;
    private Date arrivalTime;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("flight")
    private List<Seat> seats;
}
