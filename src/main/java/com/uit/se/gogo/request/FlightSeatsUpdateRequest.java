package com.uit.se.gogo.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightSeatsUpdateRequest {
    @JsonProperty("flight_id")
    private String flightId;
    private List<SeatCreationRequest> seats;
}
