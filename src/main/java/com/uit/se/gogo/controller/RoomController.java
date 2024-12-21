package com.uit.se.gogo.controller;

import com.uit.se.gogo.entity.Room;
import com.uit.se.gogo.response.DataResponse;
import com.uit.se.gogo.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
