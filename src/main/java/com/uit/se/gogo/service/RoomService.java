package com.uit.se.gogo.service;

import com.uit.se.gogo.entity.Room;
import com.uit.se.gogo.request.CreateRoomRequest;

import java.util.Optional;

public interface RoomService {
    Room findById(String id);

    Room createRoom(CreateRoomRequest request);
}
