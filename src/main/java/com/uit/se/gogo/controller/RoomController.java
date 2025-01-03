package com.uit.se.gogo.controller;

import com.uit.se.gogo.entity.Room;
import com.uit.se.gogo.entity.User;
import com.uit.se.gogo.request.CreateRoomRequest;
import com.uit.se.gogo.request.UpdateRoomRequest;
import com.uit.se.gogo.response.DataResponse;
import com.uit.se.gogo.response.PageDataResponse;
import com.uit.se.gogo.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    public DataResponse<Room> createRoom(
            @RequestParam("name") String name,
            @RequestParam("stay_id") String stayId,
            @RequestParam("base_fare") Double baseFare,
            @RequestParam(value = "discount", required = false) Double discount,
            @RequestParam(value = "tax", required = false) Double tax,
            @RequestParam(value = "service_fee", required = false) Double serviceFee,
            @RequestParam("type") String type,
            @RequestParam("max_guests") Integer maxGuests,
            @RequestParam("image") MultipartFile file) {
        User owner = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Room room = roomService.createRoom(owner, name, stayId, baseFare, discount, tax, serviceFee, type, maxGuests, file);
        return new DataResponse<>(room);
    }

    @GetMapping("/admin")
    public PageDataResponse<Room> getAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Page<Room> rooms = roomService.getAll(user.getId(), page, size);
        return new PageDataResponse<>(rooms);
    }

    @PatchMapping("/admin/{id}")
    public DataResponse<Room> updateRoom(@RequestBody UpdateRoomRequest request, @PathVariable String id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Room room = roomService.updateRoom(request, id, user);
        return new DataResponse<>(room);
    }
}
