package com.uit.se.gogo.service;

import com.uit.se.gogo.request.RoomBookingGuestInfoRequest;
import com.uit.se.gogo.request.RoomBookingRequest;

public interface RoomBookingService {
    RoomBookingRequest bookNewRoom(RoomBookingRequest roomBooking);
    void fillGuestInfo(RoomBookingGuestInfoRequest request);
}
