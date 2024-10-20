package com.uit.se.gogo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.uit.se.gogo.entity.FlightFavorite;
import com.uit.se.gogo.response.FlightFavoriteResponse;

@Mapper(componentModel = "spring")
public interface FlightFavoriteMapper {
    @Mapping(target = "roundTrip", source = "roundTrip")
    FlightFavoriteResponse toFlightFavoriteResponse(FlightFavorite flightFavorite);
}
