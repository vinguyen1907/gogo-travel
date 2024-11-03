package com.uit.se.gogo.controller;

import com.uit.se.gogo.request.RoomBookingGuestInfoRequest;
import com.uit.se.gogo.request.RoomBookingRequest;
import com.uit.se.gogo.kafka.producer.RoomBookingProducer;
import com.uit.se.gogo.response.DataResponse;
import com.uit.se.gogo.service.RoomBookingLockService;
import com.uit.se.gogo.service.RoomBookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stays/booking")
@RequiredArgsConstructor
public class RoomBookingController {
    private final RoomBookingProducer roomBookingProducer;
    private final RoomBookingLockService roomBookingLockService;
    private final RoomBookingService roomBookingService;

    @PostMapping
    public ResponseEntity<DataResponse<String>> createRoomBooking(@Valid @RequestBody RoomBookingRequest roomBooking) {
        roomBookingLockService.lockRoom(roomBooking.getRoomId());
        roomBookingService.bookNewRoom(roomBooking);
//        roomBookingProducer.sendMessage("room-booking", roomBooking);
        return ResponseEntity.ok(new DataResponse<>("Processing request."));
    }

//    @PostMapping("/lock")
//    private ResponseEntity<DataResponse<String>> lockRoomBooking(@RequestBody LockRoomRequest lockRoomRequest) {
//        roomBookingLockService.lockRoom(lockRoomRequest.getRoomId());
//        return ResponseEntity.ok(new DataResponse<>("Room locked"));
//    }

    @PostMapping("/guest-info")
    public ResponseEntity<DataResponse<String>> fillRoomBookingGuestInfo(@Valid @RequestBody RoomBookingGuestInfoRequest request) {
        roomBookingService.fillGuestInfo(request);
        return ResponseEntity.ok(new DataResponse<>("Filled guest information successfully."));
    }
}
