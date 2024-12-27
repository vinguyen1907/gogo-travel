package com.uit.se.gogo.service;

import java.util.List;

import com.uit.se.gogo.request.PaymentCreationRequest;
import com.uit.se.gogo.response.PaymentResponse;

public interface PaymentService {
    PaymentResponse createPayment(PaymentCreationRequest request);
    List<PaymentResponse> findAllByUserId(String userId);
    PaymentResponse findByBookingId(String bookingId);
    PaymentResponse findById(String id);
}
