package com.uit.se.gogo.mapper;

import org.springframework.stereotype.Component;

import com.uit.se.gogo.entity.FlightFavorite;
import com.uit.se.gogo.response.FlightFavoriteResponse;

@Component
public class FlightFavoriteMapper {
    
    public FlightFavoriteResponse toFlightFavoriteResponse(FlightFavorite flightFavorite) {
        if ( flightFavorite == null ) {
            return null;
        }

        FlightFavoriteResponse.FlightFavoriteResponseBuilder flightFavoriteResponse = FlightFavoriteResponse.builder();

        flightFavoriteResponse.roundTrip( flightFavorite.isRoundTrip() );
        flightFavoriteResponse.id( flightFavorite.getId() );
        flightFavoriteResponse.outboundFlight( flightFavorite.getOutboundFlight() );
        flightFavoriteResponse.returnFlight( flightFavorite.getReturnFlight() );
        flightFavoriteResponse.user( flightFavorite.getUser() );

        return flightFavoriteResponse.build();
    }
}