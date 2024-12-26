package com.uit.se.gogo.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.se.gogo.entity.Flight;
import com.uit.se.gogo.enums.SeatClass;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatResponse {
    private String id;
    @JsonIgnoreProperties("seats")
    private Flight flight;
    private String number;
    @JsonProperty("seat_class")
    private SeatClass seatClass;
    @JsonProperty("base_fare")
    private BigDecimal baseFare;
    @JsonProperty("service_fee")
    private BigDecimal serviceFee;
    private boolean available;
}
