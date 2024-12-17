package com.uit.se.gogo.service;

import com.uit.se.gogo.entity.RoomBooking;
import com.uit.se.gogo.request.RoomBookingGuestInfoRequest;
import com.uit.se.gogo.request.RoomBookingRequest;

public interface RoomBookingService {
    RoomBooking bookNewRoom(RoomBookingRequest roomBooking);
    void fillGuestInfo(RoomBookingGuestInfoRequest request);
}
