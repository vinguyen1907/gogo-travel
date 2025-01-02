package com.uit.se.gogo.service.impl;

import com.uit.se.gogo.entity.RoomBooking;
import com.uit.se.gogo.entity.RoomBookingLock;
import com.uit.se.gogo.exception.RoomNotAvailableException;
import com.uit.se.gogo.repository.RoomBookingLockRepository;
import com.uit.se.gogo.repository.RoomRepository;
import com.uit.se.gogo.service.RoomBookingLockService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.uit.se.gogo.constant.TimeConstant.ROOM_LOCKING_TIMEOUT_MINUTES;

@Service
@RequiredArgsConstructor
public class RoomBookingLockServiceImpl implements RoomBookingLockService {
    private final RoomBookingLockRepository roomBookingLockRepository;
    private final RoomRepository roomRepository;

    @Override
    @Transactional
    public LocalDateTime lockRoom(String roomId) {
        try {
            var room = roomRepository.findById(roomId)
                    .orElseThrow(() -> new EntityNotFoundException("Room not found"));

            roomBookingLockRepository.findActiveLockByRoomId(roomId, LocalDateTime.now())
                    .ifPresent(lock -> {
                        throw new RoomNotAvailableException(room.getName() + " is being booked by another people.");
                    });

            RoomBookingLock lock = new RoomBookingLock();
            var lockExpiration = LocalDateTime.now().plusMinutes(ROOM_LOCKING_TIMEOUT_MINUTES);
            lock.setRoom(room);
            lock.setLockTime(LocalDateTime.now());
            lock.setExpirationTime(lockExpiration);
            lock.setLocked(true);

            roomBookingLockRepository.save(lock);
            return lockExpiration;
        } catch (OptimisticLockException e) {
            throw new RoomNotAvailableException("Room is temporarily unavailable due to concurrent booking attempts.");
        }
    }

    @Transactional
    public void unlockRoom(String roomId) {
        Optional<RoomBookingLock> optionalLock = roomBookingLockRepository.findActiveLockByRoomId(roomId, LocalDateTime.now());
        if (optionalLock.isPresent()) {
            var lock = optionalLock.get();
            lock.setLocked(false);
            lock.setExpirationTime(LocalDateTime.now());
            roomBookingLockRepository.save(lock);
        } else {
            throw new EntityNotFoundException("This room is not currently locked");
        }
    }
}
