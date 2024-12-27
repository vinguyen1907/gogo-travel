package com.uit.se.gogo.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.se.gogo.enums.SeatClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatCreationRequest {
    private String number;

    @JsonProperty("departure_time_from")
    private SeatClass seatClass;

    @JsonProperty("base_fare")
    private BigDecimal baseFare;

    @JsonProperty("service_fee")
    private BigDecimal serviceFee;
    
    private boolean available;
    private BigDecimal discount;
    private BigDecimal tax; // rate
}
