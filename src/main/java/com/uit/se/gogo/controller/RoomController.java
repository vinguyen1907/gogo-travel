package com.uit.se.gogo.controller;

import com.uit.se.gogo.entity.Room;
import com.uit.se.gogo.request.CreateRoomRequest;
import com.uit.se.gogo.response.DataResponse;
import com.uit.se.gogo.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @GetMapping("{id}")
    public DataResponse<Room> getRoom(@PathVariable String id) {
        Room room = roomService.findById(id);
        return new DataResponse<>(room);
    }

    @PostMapping("/admin")
    public DataResponse<Room> createRoom(@RequestBody CreateRoomRequest request) {
        Room room = roomService.createRoom(request);
        return new DataResponse<>(room);
    }
}
