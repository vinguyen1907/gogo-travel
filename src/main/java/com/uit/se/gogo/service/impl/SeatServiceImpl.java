package com.uit.se.gogo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.uit.se.gogo.entity.Flight;
import com.uit.se.gogo.entity.Seat;
import com.uit.se.gogo.mapper.FlightMapper;
import com.uit.se.gogo.mapper.SeatMapper;
import com.uit.se.gogo.repository.FlightRepository;
import com.uit.se.gogo.repository.SeatRepository;
import com.uit.se.gogo.request.SeatCreationRequest;
import com.uit.se.gogo.response.FlightResponse;
import com.uit.se.gogo.response.SeatResponse;
import com.uit.se.gogo.service.SeatService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {
    private final SeatRepository seatRepository;
    private SeatMapper seatMapper = new SeatMapper();
    
    private FlightMapper flightMapper = new FlightMapper();
    private final FlightRepository flightRepository;

    @Override
    public SeatResponse createSeat(SeatCreationRequest request) {
        Seat seat = seatMapper.toSeat(request);
        seat = seatRepository.save(seat);
        return seatMapper.toSeatResponse(seat);
    }

    @Override
    public SeatResponse getSeat(String id) {
        Seat seat = seatRepository.findById(id).orElseThrow(() -> new RuntimeException("Seat doesn't exist"));
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
        Seat seat = seatRepository.findById(seatId).orElseThrow(() -> new RuntimeException("Seat not found"));
        if (!seat.isAvailable()) {
            throw new RuntimeException("Seat already booked");
        }
        seat.setAvailable(false);
        seatRepository.save(seat);
    }

    @Override
    public FlightResponse updateFlightSeats(String flightId, List<SeatCreationRequest> requests) {
        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new RuntimeException("Flight not found"));

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
