package com.uit.se.gogo.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightSeatsUpdateRequest {
    private String flightId;
    private List<SeatCreationRequest> seats;
}
