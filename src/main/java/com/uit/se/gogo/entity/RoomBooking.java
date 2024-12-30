package com.uit.se.gogo.entity;

import com.uit.se.gogo.enums.RoomBookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class RoomBooking extends BaseBooking {
    @Builder.Default
    private RoomBookingStatus status = RoomBookingStatus.NEW;
    @ManyToOne
    private Room room;
    @Column(nullable = false)
    private LocalDate checkinDate;
    @Column(nullable = false)
    private LocalDate checkoutDate;

    // Guest info
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String country;
}
