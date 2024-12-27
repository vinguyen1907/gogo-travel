package com.uit.se.gogo.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.uit.se.gogo.entity.Airline;
import com.uit.se.gogo.entity.Policy;
import com.uit.se.gogo.request.AirlineCreationRequest;
import com.uit.se.gogo.response.AirlineResponse;

@Component
public class AirlineMapper {
    public Airline toAirline(AirlineCreationRequest request) {
        if ( request == null ) {
            return null;
        }

        Airline airline = new Airline();

        airline.setImage( request.getImage() );
        airline.setName( request.getName() );
        List<Policy> list = request.getPolicies();
        if ( list != null ) {
            airline.setPolicies( new ArrayList<>( list ) );
        }

        return airline;
    }

    public Airline toAirline(AirlineResponse response) {
        if ( response == null ) {
            return null;
        }

        Airline airline = Airline.builder()
            .id( response.getId() )
            .image( response.getImage() )
            .name( response.getName() )
            .reviews( response.getReviews() )
            .policies( response.getPolicies() )
            .build();
        List<Policy> list = response.getPolicies();
        if ( list != null ) {
            airline.setPolicies( new ArrayList<>( list ) );
        }

        return airline;
    }

    public AirlineResponse toAirlineResponse(Airline airline) {
        if ( airline == null ) {
            return null;
        }

        AirlineResponse airlineResponse = AirlineResponse.builder()
            .id( airline.getId() )
            .image( airline.getImage() )
            .name( airline.getName() )
            .reviews( airline.getReviews() )
            .reviewCount( airline.getReviews().size() )
            .rating( airline.getRating() )
            .policies( airline.getPolicies() )
            .build();
        List<Policy> list = airline.getPolicies();
        if ( list != null ) {
            airlineResponse.setPolicies( new ArrayList<>( list ) );
        }

        return airlineResponse;
    }
}
