package com.uit.se.gogo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.uit.se.gogo.constant.EmailTemplates;
import com.uit.se.gogo.entity.FlightBooking;
import com.uit.se.gogo.entity.RoomBooking;
import com.uit.se.gogo.request.EmailRecipient;
import com.uit.se.gogo.request.EmailRequest;
import com.uit.se.gogo.request.EmailSender;
import com.uit.se.gogo.request.SendOTPRequest;
import com.uit.se.gogo.response.EmailResponse;
import com.uit.se.gogo.service.EmailService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailServiceImpl implements EmailService {

    RestTemplate restTemplate;

    static String OTP_SUBJECT = "[GOGO-Travel] - Reset Password OTP";
    static String FLIGHT_BOOKING_SUBJECT = "[GOGO-Travel] Flight Booking Information";
    static String ROOM_BOOKING_SUBJECT = "[GOGO-Travel] Flight Booking Information";


    @NonFinal
    @Value("${notification.email.brevo-url}")
    String brevoUrl;

    @NonFinal
    @Value("${notification.email.brevo-apikey}")
    String apiKey;

    @Override
    public EmailResponse resetPasswordOTP(SendOTPRequest request) {
        EmailRequest emailRequest = EmailRequest.builder()
                .sender(EmailSender.builder()
                        .name("GOGO-Travel")
                        .email("21521087@gm.uit.edu.vn")
                        .build())
                .to(List.of(request.getTo()))
                .subject(OTP_SUBJECT)
                .htmlContent(
                    EmailTemplates.generateResetPasswordEmail(request.getOtp(), request.getTo().getName())
                )
                .build();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("api-key", apiKey);

            HttpEntity<EmailRequest> httpEntity = new HttpEntity<>(emailRequest, headers);
            ResponseEntity<EmailResponse> response = restTemplate.postForEntity(
                    brevoUrl + "/v3/smtp/email",
                    httpEntity,
                    EmailResponse.class
            );

            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmailResponse flightBookingComplete(FlightBooking booking) {
        EmailRequest emailRequest = EmailRequest.builder()
                .sender(EmailSender.builder()
                        .name("GOGO-Travel")
                        .email("21521087@gm.uit.edu.vn")
                        .build())
                .to(List.of(new EmailRecipient(booking.getUser().getFullName(), booking.getUser().getEmail())))
                .subject(FLIGHT_BOOKING_SUBJECT)
                .htmlContent(
                    EmailTemplates.generateFlightBookingEmail(booking)
                )
                .build();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("api-key", apiKey);

            HttpEntity<EmailRequest> httpEntity = new HttpEntity<>(emailRequest, headers);
            ResponseEntity<EmailResponse> response = restTemplate.postForEntity(
                    brevoUrl + "/v3/smtp/email",
                    httpEntity,
                    EmailResponse.class
            );

            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmailResponse roomBookingComplete(RoomBooking booking) {
        EmailRequest emailRequest = EmailRequest.builder()
                .sender(EmailSender.builder()
                        .name("GOGO-Travel")
                        .email("21521087@gm.uit.edu.vn")
                        .build())
                .to(List.of(new EmailRecipient(booking.getUser().getFullName(), booking.getUser().getEmail())))
                .subject(FLIGHT_BOOKING_SUBJECT)
                .htmlContent(
                    EmailTemplates.generateRoomBookingEmail(booking)
                )
                .build();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("api-key", apiKey);

            HttpEntity<EmailRequest> httpEntity = new HttpEntity<>(emailRequest, headers);
            ResponseEntity<EmailResponse> response = restTemplate.postForEntity(
                    brevoUrl + "/v3/smtp/email",
                    httpEntity,
                    EmailResponse.class
            );

            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
