package com.uit.se.gogo.service;

import com.uit.se.gogo.entity.Room;
import com.uit.se.gogo.entity.User;
import com.uit.se.gogo.request.CreateRoomRequest;
import com.uit.se.gogo.request.UpdateRoomRequest;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RoomService {
    Room findById(String id);

    Room createRoom(CreateRoomRequest request);

    Page<Room> getAll(String userId, int page, int size);

    Room updateRoom(UpdateRoomRequest request, String id, User owner);

    Room createRoom(User owner,
                    String name,
                    String stayId,
                    Double baseFare,
                    Double discount,
                    Double tax,
                    Double serviceFee,
                    String type,
                    Integer maxGuests,
                    MultipartFile file);
}
