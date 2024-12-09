package com.uit.se.gogo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uit.se.gogo.entity.SeatBooking;

public interface SeatBookingRepository extends JpaRepository<SeatBooking, String>{
    List<SeatBooking> findAllByBookingId(String bookingId);
}
