package com.uit.se.gogo.mapper;

import org.mapstruct.Mapper;

import com.uit.se.gogo.entity.Airline;
import com.uit.se.gogo.request.AirlineCreationRequest;
import com.uit.se.gogo.response.AirlineResponse;

@Mapper(componentModel = "spring")
public interface AirlineMapper {
    public Airline toAirline(AirlineCreationRequest request);
    public AirlineResponse toAirlineResponse(Airline airline);
}
