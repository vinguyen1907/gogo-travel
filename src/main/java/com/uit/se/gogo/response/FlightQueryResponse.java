package com.uit.se.gogo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class FlightQueryResponse {
    private final FlightResponse outboundFlight;
    private final FlightResponse returnFlight;
    private final boolean isRoundTrip;
}
