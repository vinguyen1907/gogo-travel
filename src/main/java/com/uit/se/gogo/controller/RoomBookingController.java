package com.uit.se.gogo.controller;

import com.uit.se.gogo.entity.RoomBooking;
import com.uit.se.gogo.entity.User;
import com.uit.se.gogo.request.RoomBookingGuestInfoRequest;
import com.uit.se.gogo.request.RoomBookingRequest;
import com.uit.se.gogo.kafka.producer.RoomBookingProducer;
import com.uit.se.gogo.response.DataResponse;
import com.uit.se.gogo.response.PageDataResponse;
import com.uit.se.gogo.response.RoomBookingResponse;
import com.uit.se.gogo.service.RoomBookingLockService;
import com.uit.se.gogo.service.RoomBookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/stays/booking")
@RequiredArgsConstructor
public class RoomBookingController {
    private final RoomBookingProducer roomBookingProducer;
    private final RoomBookingLockService roomBookingLockService;
    private final RoomBookingService roomBookingService;

    @PostMapping
    public ResponseEntity<DataResponse<RoomBookingResponse>> createRoomBooking(@Valid @RequestBody RoomBookingRequest request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        request.setUserId(user.getId());
        LocalDateTime lockExpiration = roomBookingLockService.lockRoom(request.getRoomId());
        var roomBooking = roomBookingService.bookNewRoom(request);
//        roomBookingProducer.sendMessage("room-booking", roomBooking);
        return ResponseEntity.ok(new DataResponse<>(
                new RoomBookingResponse(lockExpiration, roomBooking.getId(), roomBooking.getStatus())
        ));
    }

    @PostMapping("/unlock/{roomId}")
    private ResponseEntity<DataResponse<String>> lockRoomBooking(@PathVariable String roomId) {
        roomBookingLockService.unlockRoom(roomId);
        return ResponseEntity.ok(new DataResponse<>("Unlock success"));
    }

    @PostMapping("/guest-info")
    public ResponseEntity<DataResponse<String>> fillRoomBookingGuestInfo(@Valid @RequestBody RoomBookingGuestInfoRequest request) {
        roomBookingService.fillGuestInfo(request);
        return ResponseEntity.ok(new DataResponse<>("Filled guest information successfully."));
    }

    @GetMapping("/all")
    public ResponseEntity<DataResponse<List<RoomBooking>>> getBookingsByUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<RoomBooking> bookings = roomBookingService.getRoomBookingsByUser(user.getId());
        return ResponseEntity.ok(new DataResponse<>(bookings));
    }

    @GetMapping("/admin/all")
    public ResponseEntity<PageDataResponse<RoomBooking>> getRoomBookings(@RequestParam(name = "room_id") String roomId,
                                                         @RequestParam(defaultValue = "1") int page,
                                                         @RequestParam(defaultValue = "10") int size) {
        Page<RoomBooking> booking = roomBookingService.getRoomBookings(roomId, page, size);
        return ResponseEntity.ok(new PageDataResponse<>(booking));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomBooking> getRoomBookingById(@PathVariable String id) {
        RoomBooking booking = roomBookingService.getRoomBookingById(id);
        return ResponseEntity.ok(booking);
    }
}
