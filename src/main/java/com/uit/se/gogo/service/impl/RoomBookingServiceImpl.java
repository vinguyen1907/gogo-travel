package com.uit.se.gogo.service.impl;

import com.uit.se.gogo.dto.RoomBookingDTO;
import com.uit.se.gogo.exception.RoomNotAvailableException;
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
        // check available room
        var x = roomBookingRepository.checkAvailability(
                roomBooking.getRoom().getId(),
                roomBooking.getCheckinDate(),
                roomBooking.getCheckoutDate());
        if (!x.isEmpty()) {
            throw new RoomNotAvailableException("This room is no longer available");
        }

        return new RoomBookingDTO(roomBookingRepository.save(entity));
    }
}
