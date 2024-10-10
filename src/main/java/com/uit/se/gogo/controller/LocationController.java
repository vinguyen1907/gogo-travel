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
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/${api.prefix}/locations")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LocationController {
    LocationService locationService;

    @GetMapping("/{id}")
    public LocationResponse getById(@PathVariable String id) {
        log.info("Location id: " + id);
        return locationService.findById(id);
    }

    @GetMapping
    public List<LocationResponse> getAll() {
        return locationService.findAll();
    }
    
    
    @PostMapping
    public LocationResponse postMethodName(@RequestBody LocationRequest request) {
        return locationService.createLocation(request);
    }
    
}
