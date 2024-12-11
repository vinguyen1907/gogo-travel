package com.uit.se.gogo.response;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    private Date bookingDate;

    private Date updateDate;

    @JsonIgnoreProperties("booking")
    List<SeatBooking> seats;
}
