package com.uit.se.gogo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.uit.se.gogo.entity.Seat;
import com.uit.se.gogo.request.SeatCreationRequest;
import com.uit.se.gogo.response.SeatResponse;

@Mapper(componentModel = "spring")
public interface SeatMapper {
    @Mapping(target = "available", source = "available")
    public Seat toSeat(SeatCreationRequest request);
    public SeatResponse toSeatResponse(Seat seat);
}
