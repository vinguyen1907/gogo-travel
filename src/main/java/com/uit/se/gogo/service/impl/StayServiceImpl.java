package com.uit.se.gogo.service.impl;

import com.uit.se.gogo.dto.StayDTO;
import com.uit.se.gogo.entity.*;
import com.uit.se.gogo.enums.StayType;
import com.uit.se.gogo.repository.*;
import com.uit.se.gogo.request.AdminCreateStayRequest;
import com.uit.se.gogo.request.SearchStayRequest;
import com.uit.se.gogo.service.StayService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StayServiceImpl implements StayService {
    private final StayRepository stayRepository;
    private final RoomRepository roomRepository;
    private final FeaturedImageRepository featuredImageRepository;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;

    @Override
    public Stay findById(String id) {
        var stay = stayRepository.findById(id);
        return stay.orElseThrow(() -> new EntityNotFoundException("Stay not found"));
    }

    @Override
    public Page<StayDTO> search(SearchStayRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());
        var stayPage = stayRepository.search(
                request.getCheckinDate(),
                request.getCheckoutDate(),
                request.getLocationId(),
                request.getRooms(),
                request.getGuests(),
                request.getMinPrice(),
                request.getMaxPrice(),
                request.getRating(),
                request.getType(),
                pageable
        );
        var stayDTOs = stayPage.getContent().stream().map((Object[] objects) -> {
            var id = (String) objects[0];
            List<FeaturedImage> featuredImages = featuredImageRepository.findAllByServiceId(id);
            return new StayDTO(
                    id,
                    (String) objects[1],
                    null,
                    (String) objects[2],
                    null,
                    (Double) objects[4],
                    (Integer) objects[5],
                    (StayType) objects[6],
                    (String) objects[7],
                    (Double) objects[8],
                    (Double) objects[9],
                    null,
                    featuredImages,
                    (Double) objects[10],
                    (Long) objects[11],
                    (Double) objects[12],
                    (Long) objects[13]
            );
        }).toList();
        return new PageImpl<>(stayDTOs, stayPage.getPageable(), stayPage.getTotalElements());

    }

    @Override
    public List<Room> getAvailableRooms(String stayId, LocalDate checkinDate, LocalDate checkoutDate, Integer guests) {
        return roomRepository.findAvailableRooms(stayId, guests, checkinDate, checkoutDate);
    }

    @Override
    public Stay create(AdminCreateStayRequest request, User user) {
        Location location = locationRepository.findById(request.getLocationId())
                .orElseThrow(() -> new EntityNotFoundException("Location not found"));
        Stay stay = Stay.builder()
                .owner(user)
                .address(request.getAddress())
                .location(location)
                .starRating(request.getStarRating())
                .stayType(request.getStayType())
                .overview(request.getOverview())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .build();
        return stayRepository.save(stay);
    }

    @Override
    public List<Room> getAllRooms(String stayId) {
        Stay stay = stayRepository.findById(stayId).orElseThrow(() -> new EntityNotFoundException("Stay not found"));
        return roomRepository.findAllByStay(stay);
    }

    @Override
    public List<Stay> getStaysByOwner(User owner) {
        return stayRepository.findAllByOwner(owner);
    }
}
