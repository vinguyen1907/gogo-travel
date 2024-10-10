package com.uit.se.gogo.mapper;

import org.mapstruct.Mapper;

import com.uit.se.gogo.entity.Location;
import com.uit.se.gogo.request.LocationRequest;
import com.uit.se.gogo.response.LocationResponse;

@Mapper(componentModel = "spring")
public interface  LocationMapper {
    LocationResponse toLocationResponse(Location location);
    Location toLocation(LocationRequest locationRequest);
}
