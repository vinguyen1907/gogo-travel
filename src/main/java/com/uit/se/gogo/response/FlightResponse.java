package com.uit.se.gogo.response;

import java.sql.Date;

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
    private Airport departureAirport;
    private Airport arrivalAirport;
    private String gate;
    private String timezone;
    private Date departureTime;
    private Date arrivalTime;
}
