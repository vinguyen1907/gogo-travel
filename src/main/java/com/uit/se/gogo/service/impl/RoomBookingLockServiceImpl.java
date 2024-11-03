package com.uit.se.gogo.service.impl;

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

import static com.uit.se.gogo.constant.TimeConstant.ROOM_LOCKING_TIMEOUT_MINUTES;

@Service
@RequiredArgsConstructor
public class RoomBookingLockServiceImpl implements RoomBookingLockService {
    private final RoomBookingLockRepository roomBookingLockRepository;
    private final RoomRepository roomRepository;

    @Override
    @Transactional
    public void lockRoom(String roomId) {
        try {
            var room = roomRepository.findById(roomId)
                    .orElseThrow(() -> new EntityNotFoundException("Room not found"));

            roomBookingLockRepository.findActiveLockByRoomId(roomId)
                    .ifPresent(lock -> {
                        throw new RoomNotAvailableException("Room " + roomId + " is booking by another people.");
                    });

            RoomBookingLock lock = new RoomBookingLock();
            lock.setRoom(room);
            lock.setLockTime(LocalDateTime.now());
            lock.setExpirationTime(LocalDateTime.now().plusMinutes(ROOM_LOCKING_TIMEOUT_MINUTES));
            lock.setLocked(true);

            roomBookingLockRepository.save(lock);
        } catch (OptimisticLockException e) {
            throw new RoomNotAvailableException("Room is temporarily unavailable due to concurrent booking attempts.");
        }
    }

    @Transactional
    public void unlockRoom(String roomId) {
        roomBookingLockRepository.findActiveLockByRoomId(roomId)
                .ifPresent(lock -> {
                    lock.setLocked(false);
                    lock.setExpirationTime(LocalDateTime.now());
                    roomBookingLockRepository.save(lock);
                });
    }
}
