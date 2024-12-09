package com.uit.se.gogo.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatBookingCreationRequest {
    @JsonProperty("citizen_id")
    private String citizenId;
    @JsonProperty("citizen_name")
    private String citizenName;
    @JsonProperty("seat_id")
    private String seatId;
}
