package com.uit.se.gogo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.uit.se.gogo.entity.Flight;
import com.uit.se.gogo.entity.Seat;
import com.uit.se.gogo.exception.CommonException;
import com.uit.se.gogo.mapper.FlightMapper;
import com.uit.se.gogo.mapper.SeatMapper;
import com.uit.se.gogo.repository.FlightRepository;
import com.uit.se.gogo.repository.SeatRepository;
import com.uit.se.gogo.request.SeatCreationRequest;
import com.uit.se.gogo.response.FlightResponse;
import com.uit.se.gogo.response.SeatResponse;
import com.uit.se.gogo.service.SeatService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(makeFinal=true, level=AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {
    SeatRepository seatRepository;
    SeatMapper seatMapper;
    
    FlightMapper flightMapper;
    FlightRepository flightRepository;

    @Override
    public SeatResponse createSeat(SeatCreationRequest request) {
        Seat seat = seatMapper.toSeat(request);
        seat = seatRepository.save(seat);
        return seatMapper.toSeatResponse(seat);
    }

    @Override
    public SeatResponse getSeat(String id) {
        Seat seat = seatRepository.findById(id).orElseThrow(() -> new CommonException("Seat doesn't exist"));
        return seatMapper.toSeatResponse(seat);
    }

    @Override
    public List<SeatResponse> getAllSeats() {
        return seatRepository.findAll()
            .stream()
            .map(seatMapper::toSeatResponse)
            .toList();
    }

    @Override
    public List<SeatResponse> getAvailableSeatsForFlight(String flightId) {
        return seatRepository.findByFlightIdAndAvailable(flightId, true)
            .stream()
            .map(seatMapper::toSeatResponse)
            .toList();
    }

    @Override
    public void bookSeat(String seatId) {
        Seat seat = seatRepository.findById(seatId).orElseThrow(() -> new CommonException("Seat not found"));
        if (!seat.isAvailable()) {
            throw new CommonException("Seat already booked");
        }
        seat.setAvailable(false);
        seatRepository.save(seat);
    }

    @Override
    public FlightResponse updateFlightSeats(String flightId, List<SeatCreationRequest> requests) {
        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new CommonException("Flight not found"));

        List<Seat> seatsToDelete = getAllFlightSeats(flightId);
        if (!seatsToDelete.isEmpty())
            seatRepository.deleteAll(seatsToDelete);

        List<Seat> seats = requests.stream()
            .map(seatMapper::toSeat)
            .toList();
        
        seats.forEach(seat -> seat.setFlight(flight));

        seatRepository.saveAll(seats);

        FlightResponse result = flightMapper.toFlightResponse(flight);

        result.setSeats(seats.stream()
            .map(seatMapper::toSeatResponse)
            .collect(Collectors.toList()));

        return result;
    }    

    public List<Seat> getAllFlightSeats(String flightId) {
        return seatRepository.findAllByFlightId(flightId);
    }
}
