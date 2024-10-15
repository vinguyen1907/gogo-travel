package com.uit.se.gogo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uit.se.gogo.request.AirlineCreationRequest;
import com.uit.se.gogo.response.AirlineResponse;
import com.uit.se.gogo.response.DataResponse;
import com.uit.se.gogo.service.AirlineService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/${api.prefix}/airlines")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AirlineController {
    AirlineService airlineService;
    
    @GetMapping("/{id}")
    public DataResponse<AirlineResponse> getMethodName(@PathVariable String id) {
        return DataResponse.<AirlineResponse>builder()
            .data(airlineService.findById(id))
            .build();
    }
    
    @GetMapping
    public DataResponse<List<AirlineResponse>> getAllAirports() {
        return DataResponse.<List<AirlineResponse>>builder()
            .data(airlineService.findAll())
            .build();
    }
    
    @PostMapping
    public DataResponse<AirlineResponse> postMethodName(@RequestBody AirlineCreationRequest request) {
        return DataResponse.<AirlineResponse>builder()
            .data(airlineService.createAirline(request))
            .build();
    }
}
