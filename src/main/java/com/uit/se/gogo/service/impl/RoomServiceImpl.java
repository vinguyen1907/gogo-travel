package com.uit.se.gogo.service.impl;

import com.uit.se.gogo.entity.Room;
import com.uit.se.gogo.repository.RoomRepository;
import com.uit.se.gogo.service.RoomService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    @Override
    public Room findById(String id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Room not found"));
    }
}
