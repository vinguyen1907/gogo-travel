package com.uit.se.gogo.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightCreationRequest {
    @JsonProperty("airline_id")
    private String airlineId;

    @JsonProperty("departure_airport_id")
    private String departureAirportId;

    @JsonProperty("arrival_airport_id")
    private String arrivalAirportId;

    private String gate;
    private String timezone;

    @JsonProperty("departure_time")
    private Date departureTime;

    @JsonProperty("arrival_time")
    private Date arrivalTime;
}
