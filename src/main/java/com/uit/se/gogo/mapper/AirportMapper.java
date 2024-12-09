package com.uit.se.gogo.mapper;

import org.springframework.stereotype.Component;

import com.uit.se.gogo.entity.Airport;
import com.uit.se.gogo.request.AirportCreationRequest;
import com.uit.se.gogo.response.AirportResponse;

@Component
public class AirportMapper {
    public AirportResponse toAirportResponse(Airport airport) {
        if ( airport == null ) {
            return null;
        }

        AirportResponse.AirportResponseBuilder airportResponse = AirportResponse.builder();

        airportResponse.code( airport.getCode() );
        airportResponse.id( airport.getId() );
        airportResponse.location( airport.getLocation() );
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
        airport.location( response.getLocation() );
        airport.name( response.getName() );

        return airport.build();
    }
}
