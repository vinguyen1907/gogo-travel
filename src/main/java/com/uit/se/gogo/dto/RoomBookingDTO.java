package com.uit.se.gogo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.se.gogo.entity.Room;
import com.uit.se.gogo.entity.RoomBooking;
import com.uit.se.gogo.entity.User;
import com.uit.se.gogo.enums.RoomBookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomBookingDTO {
    private String id;
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("citizen_id")
    private String citizenId;
    private RoomBookingStatus status = RoomBookingStatus.NEW;
    private Room room;
    @JsonProperty("booking_date")
    private Date bookingDate;
    @JsonProperty("checkin_date")
    private Date checkinDate;
    @JsonProperty("checkout_date")
    private Date checkoutDate;

    public RoomBookingDTO(RoomBooking roomBooking) {
        this.id = roomBooking.getId();
        this.userId = roomBooking.getUser().getId();
        this.citizenId = roomBooking.getCitizenId();
        this.status = roomBooking.getStatus();
        this.room = roomBooking.getRoom();
        this.bookingDate = roomBooking.getBookingDate();
        this.checkinDate = roomBooking.getCheckinDate();
        this.checkoutDate = roomBooking.getCheckoutDate();
    }

    public RoomBooking toEntity() {
        var entity = new RoomBooking();
        entity.setId(this.id);
        var user = new User();
        user.setId(this.userId);
        entity.setUser(user);
        entity.setCitizenId(this.citizenId);
        entity.setStatus(this.status);
        entity.setRoom(this.room);
        entity.setBookingDate(this.bookingDate);
        entity.setCheckinDate(this.checkinDate);
        entity.setCheckoutDate(this.checkoutDate);
        return entity;
    }
}
