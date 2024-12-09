package com.uit.se.gogo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uit.se.gogo.entity.FlightBooking;

public interface FlightBookingRepository extends JpaRepository<FlightBooking, String> {
    List<FlightBooking> findAllByUserId(String userId);
}
