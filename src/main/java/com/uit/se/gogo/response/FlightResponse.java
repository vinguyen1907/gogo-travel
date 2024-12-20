package com.uit.se.gogo.response;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.se.gogo.entity.Airline;
import com.uit.se.gogo.entity.Airport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlightResponse {
    private String id;
    private Airline airline;

    @JsonProperty("departure_airport")
    private Airport departureAirport;
    
    @JsonProperty("arrival_airport")
    private Airport arrivalAirport;
    
    private String gate;
    private String timezone;
    
    @JsonProperty("departure_time")
    private Date departureTime;

    @JsonProperty("arrival_time")
    private Date arrivalTime;
    @JsonIgnoreProperties("flight")
    private List<SeatResponse> seats;
}
