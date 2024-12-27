package com.uit.se.gogo.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.uit.se.gogo.enums.SeatClass;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    @JsonIgnoreProperties({"seats"})
    private Flight flight;

    private String number;

    private BigDecimal discount;
    private BigDecimal tax; // rate

    @Column(name = "seat_class")
    @Enumerated(EnumType.ORDINAL)
    private SeatClass seatClass;

    @Column(name = "base_fare", precision = 19, scale = 4)
    private BigDecimal baseFare;

    @Column(name = "service_fare", precision = 19, scale = 4)
    private BigDecimal serviceFee;
    
    @Column(name = "is_available")
    private boolean available;
}
