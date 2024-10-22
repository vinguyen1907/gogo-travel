package com.uit.se.gogo.repository;

import com.uit.se.gogo.entity.RoomBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomBookingRepository extends JpaRepository<RoomBooking, String> {
    @Query("""
    SELECT rb
    FROM RoomBooking AS rb
    WHERE rb.room.id = :roomId
    AND (
        :checkinDate BETWEEN rb.checkinDate AND rb.checkoutDate
        OR :checkoutDate BETWEEN rb.checkinDate AND rb.checkoutDate
    )
    """)
    List<RoomBooking> checkAvailability(String roomId, LocalDate checkinDate, LocalDate checkoutDate);
}
