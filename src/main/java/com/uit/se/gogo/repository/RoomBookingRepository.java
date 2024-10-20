package com.uit.se.gogo.repository;

import com.uit.se.gogo.entity.RoomBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomBookingRepository extends JpaRepository<RoomBooking, String> {
}
