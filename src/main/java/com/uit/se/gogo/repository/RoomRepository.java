package com.uit.se.gogo.repository;

import com.uit.se.gogo.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, String> {
    @Query(
            "WITH booked_rooms AS (" +
                    "SELECT b.id AS id FROM RoomBooking b " +
                    "WHERE b.checkinDate >= :checkinDate " +
                    "AND b.checkoutDate <= :checkoutDate " +
            ")" +
            "SELECT r FROM Room r " +
            "WHERE r.stay.id = :stayId " +
                    "AND r.maxGuests >= :guests " +
                    "AND r.id NOT IN (" +
                        "SELECT id FROM booked_rooms " +
                    ")"
    )
    List<Room> findAvailableRooms(String stayId, Integer guests, LocalDate checkinDate, LocalDate checkoutDate);
}
