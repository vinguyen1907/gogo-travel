package com.uit.se.gogo.service.impl;

import com.uit.se.gogo.dto.StayDTO;
import com.uit.se.gogo.entity.FeaturedImage;
import com.uit.se.gogo.entity.Room;
import com.uit.se.gogo.entity.Stay;
import com.uit.se.gogo.enums.StayType;
import com.uit.se.gogo.repository.FeaturedImageRepository;
import com.uit.se.gogo.repository.RoomRepository;
import com.uit.se.gogo.repository.StayRepository;
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
                    "Name",
                    null,
                    (String) objects[1],
                    null,
                    (Double) objects[3],
                    (Integer) objects[4],
                    (StayType) objects[5],
                    (String) objects[6],
                    (Double) objects[7],
                    (Double) objects[8],
                    null,
                    featuredImages,
                    (Double) objects[9],
                    (Long) objects[10],
                    (Double) objects[11],
                    (Long) objects[12]
            );
        }).toList();
        return new PageImpl<>(stayDTOs, stayPage.getPageable(), stayPage.getTotalElements());

    }

    @Override
    public List<Room> getAvailableRooms(String stayId, LocalDate checkinDate, LocalDate checkoutDate, Integer guests) {
        return roomRepository.findAvailableRooms(stayId, guests, checkinDate, checkoutDate);
    }
}
