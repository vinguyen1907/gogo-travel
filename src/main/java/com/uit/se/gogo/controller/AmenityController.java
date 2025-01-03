package com.uit.se.gogo.controller;

import com.uit.se.gogo.entity.Amenity;
import com.uit.se.gogo.response.DataResponse;
import com.uit.se.gogo.service.AmenityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/amenities")
@RequiredArgsConstructor
public class AmenityController {
    private final AmenityService amenityService;

    @GetMapping
    public DataResponse<List<Amenity>> getAmenities(@RequestParam(name = "is_featured", required = false, defaultValue = "true") Boolean isFeatured) {
        return new DataResponse<>(amenityService.findAll(isFeatured));
    }
}
