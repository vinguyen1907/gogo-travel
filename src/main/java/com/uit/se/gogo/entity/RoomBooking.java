package com.uit.se.gogo.entity;

import com.uit.se.gogo.enums.RoomBookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    private User user;
    private RoomBookingStatus status = RoomBookingStatus.NEW;
    @ManyToOne
    private Room room;
    @Column(nullable = false)
    private LocalDate bookingDate;
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
