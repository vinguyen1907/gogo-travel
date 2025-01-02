package com.uit.se.gogo.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uit.se.gogo.entity.FlightBooking;
import com.uit.se.gogo.entity.Room;
import com.uit.se.gogo.entity.RoomBooking;
import com.uit.se.gogo.entity.Seat;
import com.uit.se.gogo.entity.SeatBooking;
import com.uit.se.gogo.entity.Stay;
import com.uit.se.gogo.entity.User;
import com.uit.se.gogo.enums.FlightBookingStatus;
import com.uit.se.gogo.enums.RoomBookingStatus;
import com.uit.se.gogo.enums.SeatClass;
import com.uit.se.gogo.enums.UserType;
import com.uit.se.gogo.request.SendOTPRequest;
import com.uit.se.gogo.response.DataResponse;
import com.uit.se.gogo.response.EmailResponse;
import com.uit.se.gogo.service.EmailService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class EmailController {
    EmailService emailService;

    @GetMapping("/email")
    public String test() {
        return "HEllo world";
    }
    

    @PostMapping("/email/otp")
    public DataResponse<EmailResponse> sendEmail(@RequestBody SendOTPRequest request) {
        return DataResponse.<EmailResponse>builder()
                .data(emailService.resetPasswordOTP(request))
                .build();
    }

    @PostMapping("/email/room")
    public DataResponse<EmailResponse> sendEmailRoom() {
        return DataResponse.<EmailResponse>builder()
                .data(emailService.roomBookingComplete(new RoomBooking(
                    RoomBookingStatus.PAID, 
                    new Room("1", "Test", new Stay(), 100D, 0D, 0D, 10D, 
                        "", false, 10, "", new User(
                            "121", "TEst YUH", "trouwouzeppepoi-4321@yopmail.com", "01", "as", LocalDate.now(), "", "", UserType.USER, "", false
                        )), 
                        LocalDate.now(), LocalDate.now(), "Test", " YUH", "trouwouzeppepoi-4321@yopmail.com", null, null)))
                .build();
    }

    @PostMapping("/email/flight")
    public DataResponse<EmailResponse> sendEmailFlight() {
        SeatBooking seatBooking = SeatBooking.builder()
            .seat(new Seat("", null, "ABC", null, null, SeatClass.ECONOMY, null, null, false))
            .citizenId("citizenId")
            .citizenName("citizenName")
            .build();
        FlightBooking flightBooking = FlightBooking.builder()   
                .id("id") 
                .user(new User(
                    "121", "TEst YUH", "21521087@gm.uit.edu.vn", "01", "as", LocalDate.now(), "", "", UserType.USER, "", false
                ))
                .totalDiscount(BigDecimal.ONE)
                .totalBill(BigDecimal.ONE)
                .status(FlightBookingStatus.PAID)
                .bookingDate(LocalDate.now())
                .updateDate(LocalDate.now())
                .seats(List.of(seatBooking))
            .build();
        return DataResponse.<EmailResponse>builder()
                .data(emailService.flightBookingComplete(flightBooking)
                ).build();
    }
}
