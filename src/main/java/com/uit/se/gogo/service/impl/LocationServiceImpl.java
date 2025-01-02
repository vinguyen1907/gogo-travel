package com.uit.se.gogo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uit.se.gogo.entity.Location;
import com.uit.se.gogo.exception.CommonException;
import com.uit.se.gogo.mapper.LocationMapper;
import com.uit.se.gogo.repository.LocationRepository;
import com.uit.se.gogo.request.LocationCreationRequest;
import com.uit.se.gogo.response.LocationResponse;
import com.uit.se.gogo.service.LocationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService{
    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;
    
    @Override
    public LocationResponse findById(String id) {
        Location location = locationRepository.findById(id).orElseThrow(() -> new CommonException("Location not found"));
        return locationMapper.toLocationResponse(location);
    }

    @Override
    public List<LocationResponse> findAll() {
        List<Location> locations = locationRepository.findAll();
        return locations.stream().map(locationMapper::toLocationResponse).toList();
    }

    @Override
    public LocationResponse createLocation(LocationCreationRequest request) {
        Location location = locationMapper.toLocation(request);
        location = locationRepository.save(location);

        return locationMapper.toLocationResponse(location);
    }
    
}
