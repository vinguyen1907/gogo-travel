package com.uit.se.gogo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uit.se.gogo.request.LocationRequest;
import com.uit.se.gogo.response.LocationResponse;
import com.uit.se.gogo.service.LocationService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import com.uit.se.gogo.response.DataResponse;

@RestController
@RequestMapping("/${api.prefix}/locations")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LocationController {
    LocationService locationService;

    @GetMapping("/{id}")
    public DataResponse<LocationResponse> getById(@PathVariable String id) {
        return DataResponse.<LocationResponse>builder()
            .data(locationService.findById(id))
            .build();
    }

    @GetMapping
    public DataResponse<List<LocationResponse>> getAll() {
        return DataResponse.<List<LocationResponse>>builder()
            .data(locationService.findAll())
            .build();
    }
    
    
    @PostMapping
    public DataResponse<LocationResponse> postMethodName(@RequestBody LocationRequest request) {
        return DataResponse.<LocationResponse>builder()
            .data(locationService.createLocation(request))
            .build();
    }
    
}
