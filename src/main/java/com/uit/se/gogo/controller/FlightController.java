package com.uit.se.gogo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uit.se.gogo.request.FlightCreationRequest;
import com.uit.se.gogo.request.FlightFavoriteRequest;
import com.uit.se.gogo.request.FlightQueryRequest;
import com.uit.se.gogo.request.FlightSeatsUpdateRequest;
import com.uit.se.gogo.response.DataResponse;
import com.uit.se.gogo.response.FlightFavoriteResponse;
import com.uit.se.gogo.response.FlightQueryResponse;
import com.uit.se.gogo.response.FlightResponse;
import com.uit.se.gogo.response.PageDataResponse;
import com.uit.se.gogo.response.SeatResponse;
import com.uit.se.gogo.response.UserFlightFavoriteResponse;
import com.uit.se.gogo.service.FlightService;
import com.uit.se.gogo.service.SeatService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/${api.prefix}/flights")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;
    private final SeatService seatService;

    @GetMapping
    public DataResponse<List<FlightResponse>> getAllFlights() {
        return DataResponse.<List<FlightResponse>>builder()
            .data(flightService.getAllFlights())
            .build();
    }

    @GetMapping("/{id}")
    public DataResponse<FlightResponse> getFlight(@PathVariable String id) {
        return DataResponse.<FlightResponse>builder()
            .data(flightService.getFlight(id))
            .build();
    }

    @PostMapping("/filter")
    public PageDataResponse<FlightQueryResponse> filterFlights(@RequestBody FlightQueryRequest request) {
        return flightService.searchFlights(request);
    }

    @PostMapping("/admin")
    public DataResponse<FlightResponse> createFlight(@RequestBody FlightCreationRequest request) {
        return DataResponse.<FlightResponse>builder()
            .data(flightService.createFlight(request))
            .build();
    }

    @GetMapping("/{flightId}/available-seats")
    public DataResponse<List<SeatResponse>> getAvailableSeats(@PathVariable String flightId) {
        List<SeatResponse> availableSeats = seatService.getAvailableSeatsForFlight(flightId);
        return DataResponse.<List<SeatResponse>>builder().data(availableSeats).build();
    }

    @GetMapping("/seats/{seatId}")
    public DataResponse<SeatResponse> getSeatById(@PathVariable String seatId) {
        return DataResponse.<SeatResponse>builder()
            .data(seatService.getSeat(seatId))
            .build();
    }

    @PostMapping("/seats/{seatId}/book")
    public ResponseEntity<String> bookSeat(@PathVariable String seatId) {
        seatService.bookSeat(seatId);
        return ResponseEntity.ok("Seat booked successfully");
    }

    @PostMapping("/admin/seats")
    public DataResponse<FlightResponse> updateSeats(@RequestBody FlightSeatsUpdateRequest request) {
        return DataResponse.<FlightResponse>builder()
            .data(seatService.updateFlightSeats(request.getFlightId(), request.getSeats()))
            .build();
    }

    @PostMapping("/favorites")
    public DataResponse<FlightFavoriteResponse> addFlightFavorite(@RequestBody @Valid FlightFavoriteRequest request) {
        return DataResponse.<FlightFavoriteResponse>builder()
            .data(flightService.addFlightFavorite(request))
            .build();
    }
    
    @GetMapping("/favorites/{userId}")
    public DataResponse<UserFlightFavoriteResponse> getUserFlightFavorite(@PathVariable String userId) {
        return DataResponse.<UserFlightFavoriteResponse>builder()
            .data(flightService.getUserFlightFavoriteResponse(userId))
            .build();
    }
    
    
}
