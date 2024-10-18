package com.uit.se.gogo.request;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightCreationRequest {
    private String airlineId;
    private String departureAirportId;
    private String arrivalAirportId;
    private String gate;
    private String timezone;
    private Date departureTime;
    private Date arrivalTime;
}
