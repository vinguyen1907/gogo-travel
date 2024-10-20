package com.uit.se.gogo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uit.se.gogo.entity.Flight;
import com.uit.se.gogo.entity.FlightFavorite;
import com.uit.se.gogo.entity.User;
import com.uit.se.gogo.mapper.AirlineMapper;
import com.uit.se.gogo.mapper.AirportMapper;
import com.uit.se.gogo.mapper.FlightFavoriteMapper;
import com.uit.se.gogo.mapper.FlightMapper;
import com.uit.se.gogo.repository.FlightFavoriteRepository;
import com.uit.se.gogo.repository.FlightRepository;
import com.uit.se.gogo.repository.UserRepository;
import com.uit.se.gogo.request.FlightCreationRequest;
import com.uit.se.gogo.request.FlightFavoriteRequest;
import com.uit.se.gogo.response.FlightFavoriteResponse;
import com.uit.se.gogo.response.FlightResponse;
import com.uit.se.gogo.response.UserFlightFavoriteResponse;
import com.uit.se.gogo.service.AirlineService;
import com.uit.se.gogo.service.AirportService;
import com.uit.se.gogo.service.FlightService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class FlightServiceImpl implements FlightService{
    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;
    private final FlightFavoriteRepository flightFavoriteRepository;
    private final FlightFavoriteMapper flightFavoriteMapper;

    private final AirportService airportService;
    private final AirportMapper airportMapper;
    private final AirlineService airlineService;
    private final AirlineMapper airlineMapper;

    private final UserRepository userRepository;

    @Override
    public FlightResponse createFlight(FlightCreationRequest request) {
        log.info(request.toString());
        Flight flight = flightMapper.toFlight(request);

        flight.setArrivalAirport(airportMapper.toAirport(
            airportService.findById(request.getArrivalAirportId())));

        flight.setDepartureAirport(airportMapper.toAirport(
            airportService.findById(request.getDepartureAirportId())));

        flight.setAirline(airlineMapper.toAirline(
            airlineService.findById(request.getAirlineId())));

        flight = flightRepository.save(flight);
        return flightMapper.toFlightResponse(flight);
    }

    @Override
    public FlightResponse getFlight(String id) {
        Flight flight = flightRepository.findById(id).orElseThrow(() -> new RuntimeException("Flight does not exist"));
        return flightMapper.toFlightResponse(flight);
    }

    @Override
    public List<FlightResponse> getAllFlights() {
        return flightRepository.findAll()
            .stream()
            .map(flightMapper::toFlightResponse)
            .toList();
    }

    @Override
    public FlightFavoriteResponse addFlightFavorite(FlightFavoriteRequest request) {
        User user = new User();
        user.setFirstName(request.getUserId());
        user.setLastName(request.getUserId());
        Flight outboundFlight = flightRepository.findById(request.getOutboundFlightId()).orElseThrow();
        
        FlightFavorite flightFavorite = FlightFavorite.builder()
        .user(user)
        .outboundFlight(outboundFlight)
        .build();
        
        if (request.getReturnFlightId() != null) {
            Flight returnFlight = flightRepository.findById(request.getReturnFlightId()).orElseThrow();
            flightFavorite.setReturnFlight(returnFlight);
        }
        
        flightFavorite = flightFavoriteRepository.save(flightFavorite);

        return flightFavoriteMapper.toFlightFavoriteResponse(flightFavorite);
    }

    @Override
    public UserFlightFavoriteResponse getUserFlightFavoriteResponse(String userId) {
        User user = userRepository.findById(userId).orElseThrow();
        List<FlightFavoriteResponse> flightFavoriteResponses = 
            flightFavoriteRepository.findAllByUserId(userId)
                .stream()
                .map(flightFavoriteMapper::toFlightFavoriteResponse)
                .toList();
        
        return new UserFlightFavoriteResponse(user, flightFavoriteResponses);
    }

}
