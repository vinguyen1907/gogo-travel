package com.uit.se.gogo.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private SeatClass seatClass;
    private BigDecimal baseFare;
    private BigDecimal serviceFee;
    private boolean available;
}
