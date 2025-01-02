package com.uit.se.gogo.service.impl;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.uit.se.gogo.entity.Airline;
import com.uit.se.gogo.entity.Policy;
import com.uit.se.gogo.exception.CommonException;
import com.uit.se.gogo.mapper.AirlineMapper;
import com.uit.se.gogo.repository.AirlineRepository;
import com.uit.se.gogo.request.AirlineCreationRequest;
import com.uit.se.gogo.response.AirlineResponse;
import com.uit.se.gogo.service.AirlineService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AirlineServiceImpl implements AirlineService{
    private final AirlineRepository airlineRepository;
    private final AirlineMapper airlineMapper;

    @Override
    public AirlineResponse findById(String id) {
        Airline airline = airlineRepository.findById(id)
            .orElseThrow(() -> new CommonException("Airline does not exist."));
        return airlineMapper.toAirlineResponse(airline);
    }

    @Override
    public List<AirlineResponse> findAll() {
        return airlineRepository.findAll()
            .stream()
            .map(airlineMapper::toAirlineResponse)
            .toList();
    }

    @Override
    public AirlineResponse createAirline(AirlineCreationRequest request) {
        Airline airline = airlineMapper.toAirline(request);
        for (Policy policy : airline.getPolicies()) {
            policy.setAirline(airline);
        }
        try {
            airlineRepository.save(airline);
        } catch (DataIntegrityViolationException ex) {
            log.error(ex.getMessage(), ex);
            throw new CommonException("Airline name has already been taken.");
        }
        return airlineMapper.toAirlineResponse(airline);
    }
    
}
