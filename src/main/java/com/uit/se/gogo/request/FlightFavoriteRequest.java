package com.uit.se.gogo.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightFavoriteRequest {
    @NotNull(message = "Must provide user Id")
    @JsonProperty("user_id")
    private String userId;
    
    @NotNull(message = "Must provide flight Id")
    @JsonProperty("outbound_flight_id")
    private String outboundFlightId;

    @JsonProperty("return_flight_id")
    private String returnFlightId;
}
