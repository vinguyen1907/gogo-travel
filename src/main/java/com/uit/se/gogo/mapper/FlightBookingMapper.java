package com.uit.se.gogo.mapper;

import org.springframework.stereotype.Component;

import com.uit.se.gogo.entity.FlightBooking;
import com.uit.se.gogo.response.FlightBookingResponse;

@Component
public class FlightBookingMapper {
    public FlightBookingResponse toResponse(FlightBooking booking) {
        if (booking == null) {
            return null;
        }
        
        return FlightBookingResponse.builder()
                .id(booking.getId())
                .user(booking.getUser())
                .status(booking.getStatus())
                .bookingDate(booking.getBookingDate())
                .seats(booking.getSeats()) // Assuming seats already have the required structure.
                .build();
    }
}
