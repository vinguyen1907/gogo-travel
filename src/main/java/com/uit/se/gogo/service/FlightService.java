package com.uit.se.gogo.service;

import java.util.List;

import com.uit.se.gogo.request.FlightCreationRequest;
import com.uit.se.gogo.request.FlightQueryRequest;
import com.uit.se.gogo.response.FlightQueryResponse;
import com.uit.se.gogo.response.FlightResponse;

public interface FlightService {
    public FlightResponse createFlight(FlightCreationRequest request);
    public FlightResponse getFlight(String id);
    public List<FlightResponse> getAllFlights();
    public List<FlightQueryResponse> getFlightTrips(FlightQueryRequest request);
}
