package com.uit.se.gogo.service;

import java.util.List;

import com.uit.se.gogo.request.AirlineCreationRequest;
import com.uit.se.gogo.response.AirlineResponse;

public interface AirlineService {
    AirlineResponse findById(String id);
    List<AirlineResponse> findAll();
    AirlineResponse createAirline(AirlineCreationRequest request);
}
