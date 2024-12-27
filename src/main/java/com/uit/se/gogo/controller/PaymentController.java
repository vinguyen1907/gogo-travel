package com.uit.se.gogo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.uit.se.gogo.service.PaymentService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.uit.se.gogo.request.PaymentCreationRequest;
import com.uit.se.gogo.response.DataResponse;
import com.uit.se.gogo.response.PaymentResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService service;

    @PostMapping
    public DataResponse<PaymentResponse> create(@RequestBody PaymentCreationRequest request) {
        return DataResponse.<PaymentResponse>builder()
            .data(service.createPayment(request))
            .build();
    }

    @GetMapping("/{id}")
    public DataResponse<PaymentResponse> getById(@PathVariable String id) {
        return DataResponse.<PaymentResponse>builder()
            .data(service.findById(id))
            .build();
    }
    
    @GetMapping("/booking/{bookingId}")
    public DataResponse<PaymentResponse> getByBooking(@PathVariable String bookingId) {
        return DataResponse.<PaymentResponse>builder()
            .data(service.findByBookingId(bookingId))
            .build();
    }
    
    @GetMapping("/user/{userId}")
    public DataResponse<List<PaymentResponse>> getByUser(@PathVariable String userId) {
        return DataResponse.<List<PaymentResponse>>builder()
            .data(service.findAllByUserId(userId))
            .build();
    }
    
    
}
