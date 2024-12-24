package com.uit.se.gogo.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import static com.uit.se.gogo.constant.TimeConstant.SEAT_LOCKING_TIMEOUT_MINUTES;
import com.uit.se.gogo.entity.SeatBookingLock;
import com.uit.se.gogo.exception.SeatNotAvailableException;
import com.uit.se.gogo.repository.SeatBookingLockRepository;
import com.uit.se.gogo.repository.SeatRepository;
import com.uit.se.gogo.service.SeatBookingLockService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Service
@RequiredArgsConstructor
public class SeatBookingLockServiceImpl implements SeatBookingLockService {
    
    private final SeatBookingLockRepository seatBookingLockRepository;
    private final SeatRepository seatRepository;

    @Override
    @Transactional
    public LocalDateTime lockSeat(String seatId) {
        try {
            var seat = seatRepository.findById(seatId)
                    .orElseThrow(() -> new EntityNotFoundException("Seat not found"));

            seatBookingLockRepository.findActiveLockBySeatId(seatId)
                    .ifPresent(lock -> {
                        throw new SeatNotAvailableException("Seat " + seatId + " is being booked by another people.");
                    });

            SeatBookingLock lock = new SeatBookingLock();
            var lockExpiration = LocalDateTime.now().plusMinutes(SEAT_LOCKING_TIMEOUT_MINUTES);
            lock.setSeat(seat);
            lock.setLockTime(LocalDateTime.now());
            lock.setExpirationTime(lockExpiration);
            lock.setLocked(true);

            seatBookingLockRepository.save(lock);
            return lockExpiration;
        } catch (OptimisticLockException e) {
            throw new SeatNotAvailableException("Seat is temporarily unavailable due to concurrent booking attempts.");
        }
    }

    @Transactional
    @Override
    public void unlockSeat(String roomId) {
        seatBookingLockRepository.findActiveLockBySeatId(roomId)
                .ifPresent(lock -> {
                    lock.setLocked(false);
                    lock.setExpirationTime(LocalDateTime.now());
                    seatBookingLockRepository.save(lock);
                });
    }
}
