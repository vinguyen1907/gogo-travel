package com.uit.se.gogo.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.se.gogo.entity.Room;
import com.uit.se.gogo.entity.RoomBooking;
import com.uit.se.gogo.entity.User;
import jakarta.validation.constraints.Future;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomBookingRequest {
    private String id;
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("room_id")
    private String roomId;
    @JsonProperty("booking_date")
    private LocalDate bookingDate;
    @JsonProperty("checkin_date")
    @Future(message = "checkinDate must be in the future")
    private LocalDate checkinDate;
    @JsonProperty("checkout_date")
    @Future(message = "checkoutDate must be in the future")
    private LocalDate checkoutDate;

    public RoomBookingRequest(RoomBooking roomBooking) {
        this.id = roomBooking.getId();
        this.userId = roomBooking.getUser().getId();
        this.roomId = roomBooking.getRoom().getId();
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
        var room = new Room();
        room.setId(this.roomId);
        entity.setRoom(room);
        entity.setBookingDate(this.bookingDate);
        entity.setCheckinDate(this.checkinDate);
        entity.setCheckoutDate(this.checkoutDate);
        return entity;
    }
}
