package com.uit.se.gogo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uit.se.gogo.request.FlightBookingConfirmationRequest;
import com.uit.se.gogo.request.FlightBookingCreationRequest;
import com.uit.se.gogo.response.DataResponse;
import com.uit.se.gogo.response.FlightBookingResponse;
import com.uit.se.gogo.service.FlightBookingService;
import com.uit.se.gogo.service.SeatBookingLockService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/${api.prefix}/flight-booking")
@RequiredArgsConstructor
public class FlightBookingController {
    private final FlightBookingService service;
    private final SeatBookingLockService seatLockService;
    
    @PostMapping
    public DataResponse<String> createFlightBooking(@RequestBody FlightBookingCreationRequest request) {
        service.createFlightBooking(request);
        return DataResponse.<String>builder()
            .data("Success")
            .build();
    }
    
    @PostMapping("/confirm")
    public DataResponse<FlightBookingResponse> confirmFlightBooking(@RequestBody FlightBookingConfirmationRequest request) {
        return DataResponse.<FlightBookingResponse>builder()
            .data(service.confirmFlightBooking(request))
            .build();
    }
    
    @PostMapping("/unlock/{seatId}")
    public DataResponse<String> unlockSeat(@PathVariable String seatId) {
        seatLockService.unlockSeat(seatId);
        return DataResponse.<String>builder()
            .data("Success")
            .build();
    }
    
    @GetMapping("/{id}")
    public DataResponse<FlightBookingResponse> getFlightBookingById(@PathVariable String id)  {
        return DataResponse.<FlightBookingResponse>builder()
            .data(service.getFlightBookingById(id))
            .build();
    }
    
    @GetMapping("/user/{userId}")
    public DataResponse<List<FlightBookingResponse>> getUserFlightBookings(@PathVariable String userId)  {
        return DataResponse.<List<FlightBookingResponse>>builder()
            .data(service.getUserFlightBookings(userId))
            .build();
    }
    
    @PostMapping("/{id}/pay")
    public DataResponse<FlightBookingResponse> payFlightBooking(@PathVariable String id)  {
        return DataResponse.<FlightBookingResponse>builder()
            .data(service.payFlightBooking(id))
            .build();
    }
    
    @PostMapping("/{id}/cancel")
    public DataResponse<FlightBookingResponse> cancelFlightBooking(@PathVariable String id)  {
        return DataResponse.<FlightBookingResponse>builder()
            .data(service.cancelFlightBooking(id))
            .build();
    }
}
