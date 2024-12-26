package com.uit.se.gogo.response;

import java.util.Date;
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
    private Date bookingDate;
    
    @JsonProperty("update_date")
    private Date updateDate;

    @JsonIgnoreProperties("booking")
    List<SeatBooking> seats;
}
