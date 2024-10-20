package com.uit.se.gogo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.uit.se.gogo.response.AirportResponse;
import com.uit.se.gogo.response.DataResponse;
import com.uit.se.gogo.service.AirportService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.uit.se.gogo.request.AirportCreationRequest;



@RestController
@RequestMapping("/${api.prefix}/airports")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AirportController {
    AirportService airportService;

    @GetMapping("/{id}")
    public DataResponse<AirportResponse> getMethodName(@PathVariable String id) {
        return DataResponse.<AirportResponse>builder()
            .data(airportService.findById(id))
            .build();
    }
    

    @GetMapping
    public DataResponse<List<AirportResponse>> getAllAirports() {
        return DataResponse.<List<AirportResponse>>builder()
            .data(airportService.findAll())
            .build();
    }
    
    @PostMapping
    public DataResponse<AirportResponse> postMethodName(@RequestBody AirportCreationRequest request) {
        return DataResponse.<AirportResponse>builder()
            .data(airportService.createAirport(request))
            .build();
    }
    
}
