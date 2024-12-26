package com.uit.se.gogo.mapper;

import org.springframework.stereotype.Component;

import com.uit.se.gogo.entity.Airport;
import com.uit.se.gogo.request.AirportCreationRequest;
import com.uit.se.gogo.response.AirportResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AirportMapper {
    private final LocationMapper locationMapper;
    public AirportResponse toAirportResponse(Airport airport) {
        if ( airport == null ) {
            return null;
        }

        AirportResponse.AirportResponseBuilder airportResponse = AirportResponse.builder();

        airportResponse.code( airport.getCode() );
        airportResponse.id( airport.getId() );
        airportResponse.location( locationMapper.toLocationResponse(airport.getLocation()) );
        airportResponse.name( airport.getName() );

        return airportResponse.build();
    }

    public Airport toAirport(AirportCreationRequest request) {
        if ( request == null ) {
            return null;
        }

        Airport.AirportBuilder airport = Airport.builder();

        airport.code( request.getCode() );
        airport.name( request.getName() );

        return airport.build();
    }

    public Airport toAirport(AirportResponse response) {
        if ( response == null ) {
            return null;
        }

        Airport.AirportBuilder airport = Airport.builder();

        airport.code( response.getCode() );
        airport.id( response.getId() );
        airport.location( locationMapper.toLocation(response.getLocation()) );
        airport.name( response.getName() );

        return airport.build();
    }
}
