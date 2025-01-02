package com.uit.se.gogo.repository;

import com.uit.se.gogo.entity.RoomBookingLock;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface RoomBookingLockRepository extends JpaRepository<RoomBookingLock, String> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
        SELECT bl
        FROM RoomBookingLock bl
        WHERE bl.room.id = :roomId
        AND (bl.expirationTime IS NULL OR bl.expirationTime > :now)
        AND bl.isLocked = true
        """)
    Optional<RoomBookingLock> findActiveLockByRoomId(String roomId, LocalDateTime now);
}
