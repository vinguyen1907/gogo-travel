package com.uit.se.gogo.entity;

import com.uit.se.gogo.enums.RoomBookingStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
    @GeneratedValue
    private String id;
    // TODO: Replace by User
    private String userId;
    private String citizenId;
    private RoomBookingStatus status;
    @ManyToOne
    private Room room;
    private Date bookingDate;
    private Date checkinDate;
    private Date checkoutDate;
}
