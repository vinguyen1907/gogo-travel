package com.uit.se.gogo.controller;

import com.uit.se.gogo.dto.RoomBookingDTO;
import com.uit.se.gogo.kafka.producer.RoomBookingProducer;
import com.uit.se.gogo.response.DataResponse;
import com.uit.se.gogo.service.RoomBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stays/booking")
@RequiredArgsConstructor
public class RoomBookingController {
    private final RoomBookingProducer roomBookingProducer;

    @PostMapping
    public ResponseEntity<DataResponse<String>> createRoomBooking(@RequestBody RoomBookingDTO roomBooking) {
        roomBookingProducer.sendMessage("room-booking", roomBooking);
        return ResponseEntity.ok(new DataResponse<>("Processing request."));
    }
}
