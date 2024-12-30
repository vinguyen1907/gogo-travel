package com.uit.se.gogo.service;

import com.uit.se.gogo.entity.RoomBooking;
import com.uit.se.gogo.request.RoomBookingGuestInfoRequest;
import com.uit.se.gogo.request.RoomBookingRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RoomBookingService {
    RoomBooking bookNewRoom(RoomBookingRequest roomBooking);
    void fillGuestInfo(RoomBookingGuestInfoRequest request);

    Page<RoomBooking> getRoomBookings(String roomId, int page, int size);

    List<RoomBooking> getRoomBookingsByUser(String id);
}
