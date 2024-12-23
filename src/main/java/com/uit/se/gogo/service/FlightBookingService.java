package com.uit.se.gogo.service;

import java.util.List;

import com.uit.se.gogo.request.FlightBookingConfirmationRequest;
import com.uit.se.gogo.request.FlightBookingCreationRequest;
import com.uit.se.gogo.response.FlightBookingResponse;

public interface FlightBookingService {
    void createFlightBooking(FlightBookingCreationRequest request);
    FlightBookingResponse confirmFlightBooking(FlightBookingConfirmationRequest request);
    FlightBookingResponse getFlightBookingById(String id);
    List<FlightBookingResponse> getUserFlightBookings(String userId);
    FlightBookingResponse payFlightBooking(String id);
    FlightBookingResponse confirmFlightBooking(String id);
    FlightBookingResponse cancelFlightBooking(String id);
}
