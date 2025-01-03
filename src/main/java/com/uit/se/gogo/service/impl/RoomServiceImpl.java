package com.uit.se.gogo.service.impl;

import com.uit.se.gogo.entity.Room;
import com.uit.se.gogo.entity.Stay;
import com.uit.se.gogo.entity.User;
import com.uit.se.gogo.repository.RoomRepository;
import com.uit.se.gogo.repository.StayRepository;
import com.uit.se.gogo.repository.UserRepository;
import com.uit.se.gogo.request.CreateRoomRequest;
import com.uit.se.gogo.request.UpdateRoomRequest;
import com.uit.se.gogo.service.CloudinaryService;
import com.uit.se.gogo.service.RoomService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final StayRepository stayRepository;
    private final UserRepository userRepository;
    private final CloudinaryService cloudinaryService;

    @Override
    public Room findById(String id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Room not found"));
    }

    @Override
    public Room createRoom(CreateRoomRequest request) {
        Stay stay = stayRepository.findById(request.getStayId())
                .orElseThrow(() -> new EntityNotFoundException("Stay not found"));
        User user = userRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Room room = Room.builder()
                .name(request.getName())
                .stay(stay)
                .baseFare(request.getBaseFare())
                .discount(request.getDiscount())
                .tax(request.getTax())
                .serviceFee(request.getServiceFee())
                .type(request.getType())
                .isAvailable(true)
                .maxGuests(request.getMaxGuests())
                .imageUrl(request.getImageUrl())
                .owner(user)
                .build();
        return roomRepository.save(room);
    }

    @Override
    public Page<Room> getAll(String userId, int page, int size) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Pageable pageable = PageRequest.of(page, size);
        return roomRepository.findAllByOwner(user, pageable);
    }

    @Override
    public Room updateRoom(UpdateRoomRequest request, String id, User owner) {
        Room room = roomRepository.findAllByOwnerAndId(owner, id)
                .orElseThrow(() -> new EntityNotFoundException("Room not found"));
        updateIfChanged(request.getName(), room::setName, room::getName);
        updateIfChanged(request.getBaseFare(), room::setBaseFare, room::getBaseFare);
        updateIfChanged(request.getDiscount(), room::setDiscount, room::getDiscount);
        updateIfChanged(request.getTax(), room::setTax, room::getTax);
        updateIfChanged(request.getServiceFee(), room::setServiceFee, room::getServiceFee);
        updateIfChanged(request.getType(), room::setType, room::getType);
        updateIfChanged(request.getMaxGuests(), room::setMaxGuests, room::getMaxGuests);
        updateIfChanged(request.getImageUrl(), room::setImageUrl, room::getImageUrl);

        return roomRepository.save(room);
    }

    @Override
    public Room createRoom(User owner,
                           String name,
                           String stayId,
                           Double baseFare,
                           Double discount,
                           Double tax,
                           Double serviceFee,
                           String type,
                           Integer maxGuests,
                           MultipartFile file) {
        Stay stay = stayRepository.findById(stayId)
                .orElseThrow(() -> new EntityNotFoundException("Stay not found"));



        Room room = Room.builder()
                .owner(owner)
                .name(name)
                .stay(stay)
                .baseFare(baseFare)
                .discount(discount)
                .tax(tax)
                .serviceFee(serviceFee)
                .type(type)
                .maxGuests(maxGuests)
                .isAvailable(true)
                .build();
        room = roomRepository.save(room);
        var result = cloudinaryService.uploadFile(file, "gogo/room/", room.getId());
        if (result != null) {
            room.setImageUrl((String) result.get("url"));
            return roomRepository.save(room);
        }
        return room;
    }

    public static <T> void updateIfChanged(T newValue, Consumer<T> setter, Supplier<T> getter) {
        if (newValue != null && !newValue.equals(getter.get())) {
            setter.accept(newValue);
        }
    }

}
