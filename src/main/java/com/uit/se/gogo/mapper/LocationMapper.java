package com.uit.se.gogo.mapper;

import com.uit.se.gogo.entity.Location;
import com.uit.se.gogo.request.LocationCreationRequest;
import com.uit.se.gogo.response.LocationResponse;

public class LocationMapper {
    public LocationResponse toLocationResponse(Location location) {
        if ( location == null ) {
            return null;
        }

        return LocationResponse.builder()
            .city( location.getCity() )
            .country( location.getCountry() )
            .description( location.getDescription() )
            .id( location.getId() )
            .build();
    }

    public Location toLocation(LocationCreationRequest locationRequest) {
        if ( locationRequest == null ) {
            return null;
        }

        return Location.builder()
            .city( locationRequest.getCity() )
            .country( locationRequest.getCountry() )
            .description( locationRequest.getDescription() )
            .build();
    }
}
