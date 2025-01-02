package com.uit.se.gogo.service.impl;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;

import com.uit.se.gogo.entity.BankCard;
import com.uit.se.gogo.entity.BaseBooking;
import com.uit.se.gogo.entity.FlightBooking;
import com.uit.se.gogo.entity.Payment;
import com.uit.se.gogo.entity.RoomBooking;
import com.uit.se.gogo.enums.BookingType;
import com.uit.se.gogo.enums.FlightBookingStatus;
import com.uit.se.gogo.enums.RoomBookingStatus;
import com.uit.se.gogo.exception.CommonException;
import com.uit.se.gogo.repository.BankCardRepository;
import com.uit.se.gogo.repository.FlightBookingRepository;
import com.uit.se.gogo.repository.PaymentRepository;
import com.uit.se.gogo.repository.RoomBookingRepository;
import com.uit.se.gogo.request.PaymentCreationRequest;
import com.uit.se.gogo.response.PaymentResponse;
import com.uit.se.gogo.service.EmailService;
import com.uit.se.gogo.service.PaymentService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE, makeFinal=true)
public class PaymentServiceImpl implements PaymentService {

    PaymentRepository repository;
    BankCardRepository cardRepository;
    FlightBookingRepository flightBookingRepository;
    RoomBookingRepository roomBookingRepository;

    EmailService emailService;

    @Override
    public PaymentResponse createPayment(PaymentCreationRequest request) {
        BankCard card = cardRepository.findById(request.getCardId())
            .orElseThrow(() -> new CommonException("Card not found"));
            BaseBooking booking;

        if (request.getType() == BookingType.FLIGHT) {
            booking = flightBookingRepository.findById(request.getBookingId())
                .orElseThrow(() -> new CommonException("Booking not found"));
        } else {
            booking = roomBookingRepository.findById(request.getBookingId())
                .orElseThrow(() -> new CommonException("Booking not found"));
        }

        Payment payment = Payment.builder()
            .card(card)
            .user(card.getUser())
            .booking(booking)
            .paymentTime(Instant.now())
            .type(request.getType())
            .build();

        payment = repository.save(payment);

        try {
            Thread.sleep(3000); // Introduce a 3-second delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted status
            throw new CommonException("Thread was interrupted");
        }

        switch (booking) {
            case FlightBooking flightBooking -> {
                flightBooking.setStatus(FlightBookingStatus.PAID);
                flightBookingRepository.save(flightBooking);
                emailService.flightBookingComplete(flightBooking);
            }
            case RoomBooking roomBooking -> {
                roomBooking.setStatus(RoomBookingStatus.PAID);
                roomBookingRepository.save(roomBooking);
                emailService.roomBookingComplete(roomBooking);
            }
            default -> {
            }
        }

        return payment.toResponse();
    }

    @Override
    public List<PaymentResponse> findAllByUserId(String userId) {
        return repository.findAllByUserId(userId).stream()
            .map(Payment::toResponse).toList();
    }

    @Override
    public PaymentResponse findByBookingId(String bookingId) {
        return repository.findByBookingId(bookingId).toResponse();
    }

    @Override
    public PaymentResponse findById(String id) {
        Payment payment = repository.findById(id).orElseThrow(() -> new CommonException("Payment not found"));
        return payment.toResponse();
    }
    
}
