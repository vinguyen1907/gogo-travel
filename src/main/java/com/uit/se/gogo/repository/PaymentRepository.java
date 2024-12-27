package com.uit.se.gogo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uit.se.gogo.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, String> {
    List<Payment> findAllByUserId(String userId);
    Payment findByBookingId(String bookingId);
}
