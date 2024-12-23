package com.uit.se.gogo.service.impl;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uit.se.gogo.entity.FlightBooking;
import com.uit.se.gogo.entity.Seat;
import com.uit.se.gogo.entity.SeatBooking;
import com.uit.se.gogo.entity.User;
import com.uit.se.gogo.enums.FlightBookingStatus;
import com.uit.se.gogo.mapper.FlightBookingMapper;
import com.uit.se.gogo.repository.FlightBookingRepository;
import com.uit.se.gogo.repository.SeatRepository;
import com.uit.se.gogo.repository.UserRepository;
import com.uit.se.gogo.request.FlightBookingConfirmationRequest;
import com.uit.se.gogo.request.FlightBookingCreationRequest;
import com.uit.se.gogo.request.SeatBookingCreationRequest;
import com.uit.se.gogo.response.FlightBookingResponse;
import com.uit.se.gogo.service.FlightBookingService;
import com.uit.se.gogo.service.SeatBookingLockService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FlightBookingServiceImpl implements FlightBookingService {
    private final FlightBookingRepository flightBookingRepository;
    private final FlightBookingMapper mapper;
    
    private final UserRepository userRepository;
    private final SeatRepository seatRepository;

    private final SeatBookingLockService seatLockService;

    @Override
    public void createFlightBooking(FlightBookingCreationRequest request) {
        for (String seatId : request.getSeatIds()) {
            seatLockService.lockSeat(seatId);
        }
    }

    @Override
    @Transactional
    public FlightBookingResponse confirmFlightBooking(FlightBookingConfirmationRequest request) {
        User user = userRepository.findById(request.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        Map<String, SeatBookingCreationRequest> seatMap = request.getSeats().stream()
            .collect(Collectors.toMap(SeatBookingCreationRequest::getSeatId, seat -> seat));
        List<Seat> seats = seatRepository.findAllById(List.copyOf(seatMap.keySet()));
        if (seats.isEmpty()) throw new RuntimeException("Seat list in flight booking can not be empty");

        for (Seat seat : seats) {
            if (!seat.isAvailable()) throw new RuntimeException("Seat " + seat.getNumber() + " is unavailable." );
            seat.setAvailable(false);
            seatRepository.save(seat);
        }
        
        final FlightBooking booking = FlightBooking.builder()
            .bookingDate(Date.from(Instant.now()))
            .updateDate(Date.from(Instant.now()))
            .user(user)
            .status(FlightBookingStatus.PENDING)
            .build();
        List<SeatBooking> seatBookings = seats.stream().map(seat -> 
            SeatBooking.builder()
                .booking(booking)
                .citizenId(seatMap.get(seat.getId()).getCitizenId())
                .citizenName(seatMap.get(seat.getId()).getCitizenName())
                .seat(seat)
                .build()
        ).toList();

        booking.setSeats(seatBookings);

        FlightBooking savedBooking = flightBookingRepository.save(booking);
        return mapper.toResponse(savedBooking);
    }

    @Override
    public FlightBookingResponse getFlightBookingById(String id) {
        FlightBooking booking = flightBookingRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Booking not found"));
        return mapper.toResponse(booking);
    }

    @Override
    public List<FlightBookingResponse> getUserFlightBookings(String userId) {
        return flightBookingRepository.findAllByUserId(userId).stream()
            .map(mapper::toResponse)
            .toList();
    }

    @Override
    public FlightBookingResponse payFlightBooking(String id) {
        FlightBooking booking = flightBookingRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setStatus(FlightBookingStatus.PAID);
        booking.setUpdateDate(Date.from(Instant.now()));
        return mapper.toResponse(booking);
    }

    @Override
    public FlightBookingResponse confirmFlightBooking(String id) {
        FlightBooking booking = flightBookingRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setStatus(FlightBookingStatus.CONFIRMED);
        booking.setUpdateDate(Date.from(Instant.now()));
        return mapper.toResponse(booking);
    }

    @Override
    public FlightBookingResponse cancelFlightBooking(String id) {
        FlightBooking booking = flightBookingRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setStatus(FlightBookingStatus.CANCELLED);
        booking.setUpdateDate(Date.from(Instant.now()));
        return mapper.toResponse(booking);
    }
    
}
