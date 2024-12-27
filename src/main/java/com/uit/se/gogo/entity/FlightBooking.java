package com.uit.se.gogo.entity;

import java.time.LocalDate;
import java.util.List;

import com.uit.se.gogo.enums.FlightBookingStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class FlightBooking extends BaseBooking {
    private FlightBookingStatus status;
    private LocalDate updateDate;
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    List<SeatBooking> seats;
}
