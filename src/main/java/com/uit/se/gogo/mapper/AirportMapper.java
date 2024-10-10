package com.uit.se.gogo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.uit.se.gogo.entity.Airport;
import com.uit.se.gogo.request.AirportRequest;
import com.uit.se.gogo.response.AirportResponse;

@Mapper(componentModel = "spring")
public interface AirportMapper {
    AirportResponse toAirportResponse(Airport airport);

    @Mapping(target = "location", ignore = true)
    Airport toAirport(AirportRequest request);
}
