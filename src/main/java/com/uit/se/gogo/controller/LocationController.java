package com.uit.se.gogo.controller;

import com.uit.se.gogo.entity.Location;
import com.uit.se.gogo.response.DataResponse;
import com.uit.se.gogo.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/locations")
@RequiredArgsConstructor
public class LocationController {
    private final LocationService locationService;

    @GetMapping
    public ResponseEntity<DataResponse<List<Location>>> getAllLocations() {
        var locations = locationService.findAll();
        return ResponseEntity.ok(new DataResponse<>(locations));
    }
}
