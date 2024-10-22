package com.uit.se.gogo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.se.gogo.entity.Flight;
import com.uit.se.gogo.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightFavoriteResponse {
    private String id;
    private User user;

    @JsonIgnoreProperties("seats")
    @JsonProperty("outbound_flight")
    private Flight outboundFlight;
    
    @JsonIgnoreProperties("seats")
    @JsonProperty("return_flight")
    private Flight returnFlight;

    @JsonProperty("round_trip")
    private boolean roundTrip;
}
