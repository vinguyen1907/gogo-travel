package com.uit.se.gogo.service;

import java.util.List;

import com.uit.se.gogo.request.SeatCreationRequest;
import com.uit.se.gogo.response.FlightResponse;
import com.uit.se.gogo.response.SeatResponse;

public interface SeatService {
    public SeatResponse createSeat(SeatCreationRequest request);
    public SeatResponse getSeat(String id);
    public List<SeatResponse> getAllSeats();
    public List<SeatResponse> getAvailableSeatsForFlight(String flightId);
    public void bookSeat(String seatId);
    public FlightResponse updateFlightSeats(String flightId, List<SeatCreationRequest> requests);
}
