package com.uit.se.gogo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uit.se.gogo.entity.Location;
import com.uit.se.gogo.mapper.LocationMapper;
import com.uit.se.gogo.repository.LocationRepository;
import com.uit.se.gogo.request.LocationCreationRequest;
import com.uit.se.gogo.response.LocationResponse;
import com.uit.se.gogo.service.LocationService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LocationServiceImpl implements LocationService{
    LocationRepository locationRepository;
    LocationMapper locationMapper;
    
    @Override
    public LocationResponse findById(String id) {
        Location location = locationRepository.findById(id).orElseThrow(RuntimeException::new);
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
