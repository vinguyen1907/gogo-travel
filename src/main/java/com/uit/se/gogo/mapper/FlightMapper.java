package com.uit.se.gogo.mapper;

import org.mapstruct.Mapper;

import com.uit.se.gogo.entity.Flight;
import com.uit.se.gogo.request.FlightCreationRequest;
import com.uit.se.gogo.response.FlightResponse;

@Mapper(componentModel = "spring")
public interface FlightMapper {
    public Flight toFlight(FlightCreationRequest request);
    public FlightResponse toFlightResponse(Flight flight);
}
