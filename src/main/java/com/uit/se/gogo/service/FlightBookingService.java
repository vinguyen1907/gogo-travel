package com.uit.se.gogo.service;

import java.util.List;

import com.uit.se.gogo.request.FlightBookingCreationRequest;
import com.uit.se.gogo.response.FlightBookingResponse;

public interface FlightBookingService {
    FlightBookingResponse createFlightBooking(FlightBookingCreationRequest request);
    FlightBookingResponse getFlightBookingById(String id);
    List<FlightBookingResponse> getUserFlightBookings(String userId);
    FlightBookingResponse payFlightBooking(String id);
    FlightBookingResponse confirmFlightBooking(String id);
    FlightBookingResponse cancelFlightBooking(String id);
}
