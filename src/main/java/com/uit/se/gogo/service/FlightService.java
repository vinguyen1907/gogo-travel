package com.uit.se.gogo.service;

import java.util.List;

import com.uit.se.gogo.request.FlightCreationRequest;
import com.uit.se.gogo.request.FlightFavoriteRequest;
import com.uit.se.gogo.request.FlightQueryRequest;
import com.uit.se.gogo.response.FlightFavoriteResponse;
import com.uit.se.gogo.response.FlightQueryResponse;
import com.uit.se.gogo.response.FlightResponse;
import com.uit.se.gogo.response.PageDataResponse;
import com.uit.se.gogo.response.UserFlightFavoriteResponse;

public interface FlightService {
    public FlightResponse createFlight(FlightCreationRequest request);
    public FlightResponse getFlight(String id);
    public List<FlightResponse> getAllFlights();
    public FlightFavoriteResponse addFlightFavorite(FlightFavoriteRequest request);
    public UserFlightFavoriteResponse getUserFlightFavoriteResponse(String userId);
     public PageDataResponse<FlightQueryResponse> searchFlights(FlightQueryRequest request);
}
