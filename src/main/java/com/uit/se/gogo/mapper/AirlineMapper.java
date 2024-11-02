package com.uit.se.gogo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.uit.se.gogo.entity.Airline;
import com.uit.se.gogo.request.AirlineCreationRequest;
import com.uit.se.gogo.response.AirlineResponse;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AirlineMapper {
    public Airline toAirline(AirlineCreationRequest request);
    public Airline toAirline(AirlineResponse response);
    public AirlineResponse toAirlineResponse(Airline airline);
}
