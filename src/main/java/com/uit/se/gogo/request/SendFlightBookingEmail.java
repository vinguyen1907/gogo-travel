package com.uit.se.gogo.request;

import com.uit.se.gogo.response.FlightBookingResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendFlightBookingEmail {
    EmailRecipient to;
    FlightBookingResponse flightBooking;    
}
