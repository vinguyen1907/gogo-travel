package com.uit.se.gogo.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.se.gogo.entity.SeatBooking;
import com.uit.se.gogo.entity.User;
import com.uit.se.gogo.enums.FlightBookingStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightBookingResponse {
    private String id;

    private User user;

    private FlightBookingStatus status;

    @JsonProperty("booking_date")
    private LocalDate bookingDate;
    
    @JsonProperty("update_date")
    private LocalDate updateDate;
    
    @JsonProperty("total_discount")
    private BigDecimal totalDiscount;
    
    @JsonProperty("total_bill")
    private BigDecimal totalBill;

    @JsonIgnoreProperties("booking")
    List<SeatBooking> seats;
}
