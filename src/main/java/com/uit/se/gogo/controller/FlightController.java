package com.uit.se.gogo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uit.se.gogo.request.FlightCreationRequest;
import com.uit.se.gogo.response.DataResponse;
import com.uit.se.gogo.response.FlightResponse;
import com.uit.se.gogo.service.FlightService;

import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/${api.prefix}/flights")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;

    @GetMapping
    public DataResponse<List<FlightResponse>> getAllFlights() {
        return DataResponse.<List<FlightResponse>>builder()
            .data(flightService.getAllFlights())
            .build();
    }

    @GetMapping("/{id}")
    public DataResponse<FlightResponse> getFlight(@PathVariable String id) {
        return DataResponse.<FlightResponse>builder()
            .data(flightService.getFlight(id))
            .build();
    }

    @PostMapping
    public DataResponse<FlightResponse> createFlight(@RequestBody FlightCreationRequest request) {
        return DataResponse.<FlightResponse>builder()
            .data(flightService.createFlight(request))
            .build();
    }
    
    
}
