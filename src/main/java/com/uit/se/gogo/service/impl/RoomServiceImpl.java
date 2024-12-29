package com.uit.se.gogo.service.impl;

import com.uit.se.gogo.entity.Room;
import com.uit.se.gogo.entity.Stay;
import com.uit.se.gogo.entity.User;
import com.uit.se.gogo.repository.RoomRepository;
import com.uit.se.gogo.repository.StayRepository;
import com.uit.se.gogo.repository.UserRepository;
import com.uit.se.gogo.request.CreateRoomRequest;
import com.uit.se.gogo.service.RoomService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final StayRepository stayRepository;
    private final UserRepository userRepository;

    @Override
    public Room findById(String id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Room not found"));
    }

    @Override
    public Room createRoom(CreateRoomRequest request) {
        Stay stay = stayRepository.findById(request.getStayId())
                .orElseThrow(() -> new EntityNotFoundException("Stay not found"));
        User user = userRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Room room = Room.builder()
                .name(request.getName())
                .stay(stay)
                .baseFare(request.getBaseFare())
                .discount(request.getDiscount())
                .tax(request.getTax())
                .serviceFee(request.getServiceFee())
                .type(request.getType())
                .isAvailable(true)
                .maxGuests(request.getMaxGuests())
                .imageUrl(request.getImageUrl())
                .owner(user)
                .build();
        return roomRepository.save(room);
    }
}
