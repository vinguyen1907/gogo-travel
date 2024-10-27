package com.uit.se.gogo.entity;

import com.uit.se.gogo.enums.RoomBookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
    private Date bookingDate;
    private Date checkinDate;
    private Date checkoutDate;
}
