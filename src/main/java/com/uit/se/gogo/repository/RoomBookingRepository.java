package com.uit.se.gogo.repository;

import com.uit.se.gogo.entity.RoomBooking;
import com.uit.se.gogo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        (:checkinDate < rb.checkoutDate AND :checkoutDate > rb.checkinDate)
    )
    """)
    List<RoomBooking> checkAvailability(String roomId, LocalDate checkinDate, LocalDate checkoutDate);

    Page<RoomBooking> findByRoomId(String roomId, Pageable pageable);

    List<RoomBooking> findAllByUser(User user);
}
