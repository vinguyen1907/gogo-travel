package com.uit.se.gogo.service;

import java.util.List;

import com.uit.se.gogo.request.AirportRequest;
import com.uit.se.gogo.response.AirportResponse;

public interface AirportService {
    AirportResponse findById(String id);
    List<AirportResponse> findAll();
    AirportResponse createAirport(AirportRequest request);
}
