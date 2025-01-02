package com.uit.se.gogo.service;

import com.uit.se.gogo.entity.FlightBooking;
import com.uit.se.gogo.entity.RoomBooking;
import com.uit.se.gogo.request.SendOTPRequest;
import com.uit.se.gogo.response.EmailResponse;

public interface EmailService {
    EmailResponse resetPasswordOTP(SendOTPRequest request);
    EmailResponse flightBookingComplete(FlightBooking flightBooking);
    EmailResponse roomBookingComplete(RoomBooking roomBooking);
}
