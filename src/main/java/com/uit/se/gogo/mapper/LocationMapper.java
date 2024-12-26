package com.uit.se.gogo.mapper;

import org.springframework.stereotype.Component;

import com.uit.se.gogo.entity.Location;
import com.uit.se.gogo.request.LocationCreationRequest;
import com.uit.se.gogo.response.LocationResponse;

@Component
public class LocationMapper {
    public LocationResponse toLocationResponse(Location location) {
        if ( location == null ) {
            return null;
        }

        return LocationResponse.builder()
            .city( location.getCity() )
            .country( location.getCountry() )
            .description( location.getDescription() )
            .imageUrl( location.getImageUrl() )
            .id( location.getId() )
            .build();
    }

    public Location toLocation(LocationResponse response) {
        if ( response == null ) {
            return null;
        }

        return Location.builder()
            .city( response.getCity() )
            .country( response.getCountry() )
            .description( response.getDescription() )
            .imageUrl( response.getImageUrl() )
            .id( response.getId() )
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
            .imageUrl( locationRequest.getImageUrl() )
            .build();
    }
}
