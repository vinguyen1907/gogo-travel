package com.uit.se.gogo.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightBookingConfirmationRequest {
    @JsonProperty("user_id")
    private String userId;

    private List<SeatBookingCreationRequest> seats;
}
