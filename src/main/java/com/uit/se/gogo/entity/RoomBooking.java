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
    private String citizenId;
    private RoomBookingStatus status;
    @ManyToOne
    private Room room;
    private LocalDate bookingDate;
    private LocalDate checkinDate;
    private LocalDate checkoutDate;
}
