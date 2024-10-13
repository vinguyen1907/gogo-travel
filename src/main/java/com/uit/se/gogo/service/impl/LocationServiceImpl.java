package com.uit.se.gogo.service.impl;

import com.uit.se.gogo.entity.Location;
import com.uit.se.gogo.repository.LocationRepository;
import com.uit.se.gogo.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }
}
