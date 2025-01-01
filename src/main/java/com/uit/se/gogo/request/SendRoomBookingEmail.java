package com.uit.se.gogo.request;

import com.uit.se.gogo.entity.RoomBooking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendRoomBookingEmail {
    EmailRecipient to;
    RoomBooking roomBooking;
}
