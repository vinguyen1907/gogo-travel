package com.uit.se.gogo.service;

import com.uit.se.gogo.request.SendFlightBookingEmail;
import com.uit.se.gogo.request.SendOTPRequest;
import com.uit.se.gogo.request.SendRoomBookingEmail;
import com.uit.se.gogo.response.EmailResponse;

public interface EmailService {
    EmailResponse resetPasswordOTP(SendOTPRequest request);
    EmailResponse flightBookingComplete(SendFlightBookingEmail request);
    EmailResponse roomBookingComplete(SendRoomBookingEmail request);
}
