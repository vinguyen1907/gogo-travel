package com.uit.se.gogo.service.impl;

import com.uit.se.gogo.entity.RoomBooking;
import com.uit.se.gogo.entity.User;
import com.uit.se.gogo.enums.RoomBookingStatus;
import com.uit.se.gogo.repository.UserRepository;
import com.uit.se.gogo.request.RoomBookingGuestInfoRequest;
import com.uit.se.gogo.request.RoomBookingRequest;
import com.uit.se.gogo.exception.RoomNotAvailableException;
import com.uit.se.gogo.repository.RoomBookingRepository;
import com.uit.se.gogo.service.RoomBookingLockService;
import com.uit.se.gogo.service.RoomBookingService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomBookingServiceImpl implements RoomBookingService {
    private final RoomBookingRepository roomBookingRepository;
    private final RoomBookingLockService roomBookingLockService;
    private final UserRepository userRepository;

    @Override
    public RoomBooking bookNewRoom(RoomBookingRequest roomBooking) {
        var entity = roomBooking.toEntity();
        // check available room
        var x = roomBookingRepository.checkAvailability(
                roomBooking.getRoomId(),
                roomBooking.getCheckinDate(),
                roomBooking.getCheckoutDate());
        if (!x.isEmpty()) {
            roomBookingLockService.unlockRoom(roomBooking.getRoomId());
            throw new RoomNotAvailableException("This room is no longer available");
        }

        return roomBookingRepository.save(entity);
    }

    @Override
    public void fillGuestInfo(RoomBookingGuestInfoRequest request) {
        var booking = roomBookingRepository.findById(request.getBookingId())
                .orElseThrow(() -> new EntityNotFoundException("Room booking not found."));

        booking.setFirstName(request.getFirstName());
        booking.setLastName(request.getLastName());
        booking.setEmail(request.getEmail());
        booking.setPhone(request.getPhone());
        booking.setCountry(request.getCountry());
        booking.setStatus(RoomBookingStatus.FILLED_INFO);

        roomBookingRepository.save(booking);
    }

    @Override
    public Page<RoomBooking> getRoomBookings(String roomId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return roomBookingRepository.findByRoomId(roomId, pageable);
    }

    @Override
    public List<RoomBooking> getRoomBookingsByUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found."));
        return roomBookingRepository.findAllByUser(user);
    }
}
