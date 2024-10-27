package com.uit.se.gogo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlightQueryResponse {
    @JsonIgnoreProperties("seats")
    @JsonProperty("outbound_flight")
    private FlightResponse outboundFlight;

    @JsonIgnoreProperties("seats")
    @JsonProperty("return_flight")
    private FlightResponse returnFlight;

    @JsonProperty("round_trip")
    private boolean roundTrip;
}
