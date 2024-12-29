package com.uit.se.gogo.service;

import com.uit.se.gogo.entity.Room;
import com.uit.se.gogo.entity.User;
import com.uit.se.gogo.request.CreateRoomRequest;
import com.uit.se.gogo.request.UpdateRoomRequest;
import org.springframework.data.domain.Page;

public interface RoomService {
    Room findById(String id);

    Room createRoom(CreateRoomRequest request);

    Page<Room> getAll(String userId, int page, int size);

    Room updateRoom(UpdateRoomRequest request, String id, User owner);
}
