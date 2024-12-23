package com.uit.se.gogo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import com.uit.se.gogo.entity.SeatBookingLock;

import jakarta.persistence.LockModeType;

public interface SeatBookingLockRepository extends JpaRepository<SeatBookingLock, String>{
    
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
        SELECT bl
        FROM RoomBookingLock bl
        WHERE bl.room.id = :seatId
        AND (bl.expirationTime IS NULL OR bl.expirationTime > CURRENT_TIMESTAMP)
        """)
    Optional<SeatBookingLock> findActiveLockBySeatId(String seatId);
}
