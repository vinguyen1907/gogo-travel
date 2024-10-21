package com.uit.se.gogo.service.impl;

import com.uit.se.gogo.dto.RoomBookingDTO;
import com.uit.se.gogo.repository.RoomBookingRepository;
import com.uit.se.gogo.service.RoomBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomBookingServiceImpl implements RoomBookingService {
    private final RoomBookingRepository roomBookingRepository;

    @Override
    public RoomBookingDTO bookNewRoom(RoomBookingDTO roomBooking) {
        var entity = roomBooking.toEntity();
        return new RoomBookingDTO(roomBookingRepository.save(entity));
    }
}
