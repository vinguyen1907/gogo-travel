package com.uit.se.gogo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uit.se.gogo.entity.Airport;
import com.uit.se.gogo.mapper.AirportMapper;
import com.uit.se.gogo.repository.AirportRepository;
import com.uit.se.gogo.repository.LocationRepository;
import com.uit.se.gogo.request.AirportCreationRequest;
import com.uit.se.gogo.response.AirportResponse;
import com.uit.se.gogo.service.AirportService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService{
    private final AirportRepository airportRepository;
    private AirportMapper airportMapper;

    private final LocationRepository locationRepository;

    @Override
    public AirportResponse findById(String id) {
        Airport airport = airportRepository.findById(id).orElseThrow(RuntimeException::new);
        return airportMapper.toAirportResponse(airport);
    }

    @Override
    public List<AirportResponse> findAll() {
        return airportRepository.findAll()
            .stream()
            .map(airportMapper::toAirportResponse)
            .toList();
    }

    @Override
    public AirportResponse createAirport(AirportCreationRequest request) {
        Airport airport = airportMapper.toAirport(request);
        var location = locationRepository.findById(request.getLocation()).orElseThrow(RuntimeException::new);
        airport.setLocation(location);
        airport = airportRepository.save(airport);
        return airportMapper.toAirportResponse(airport);
    }

}
