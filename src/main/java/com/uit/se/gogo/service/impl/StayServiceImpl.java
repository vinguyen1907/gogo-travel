package com.uit.se.gogo.service.impl;

import com.uit.se.gogo.dto.StayDTO;
import com.uit.se.gogo.entity.*;
import com.uit.se.gogo.enums.StayType;
import com.uit.se.gogo.repository.*;
import com.uit.se.gogo.request.AdminCreateStayRequest;
import com.uit.se.gogo.request.SearchStayRequest;
import com.uit.se.gogo.service.AmenityService;
import com.uit.se.gogo.service.StayService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StayServiceImpl implements StayService {
    private final StayRepository stayRepository;
    private final RoomRepository roomRepository;
    private final FeaturedImageRepository featuredImageRepository;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;
    private final AmenityService amenityService;
    private final ServiceAmenityServiceImpl serviceAmenityServiceImpl;
    private final CloudinaryServiceImpl cloudinaryServiceImpl;

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

    @Override
    public Stay create(User owner,
                       String name,
                       String address,
                       String locationId,
                       Integer starRating,
                       StayType stayType,
                       String overview,
                       Double latitude,
                       Double longitude,
                       List<String> amenities,
                       MultipartFile image1,
                       MultipartFile image2) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new EntityNotFoundException("Location not found"));
        Stay stay = Stay.builder()
                .owner(owner)
                .name(name)
                .address(address)
                .location(location)
                .starRating(starRating)
                .stayType(stayType)
                .overview(overview)
                .latitude(latitude)
                .longitude(longitude)
                .build();
        stay = stayRepository.save(stay);

        List<Amenity> amenityEntities = amenityService.findAllByIds(amenities);
        List<ServiceAmenity> savedAmenities = new ArrayList<>();
        for (Amenity amenity : amenityEntities) {
            var newAmenity = serviceAmenityServiceImpl.save(
                    ServiceAmenity.builder()
                            .amenity(amenity)
                            .service(stay)
                            .build()
            );
            savedAmenities.add(newAmenity);
        }

        var result1 = cloudinaryServiceImpl.uploadFile(image1, "gogo/stay/", stay.getId() + "-1");
        var result2 = cloudinaryServiceImpl.uploadFile(image2, "gogo/stay/", stay.getId() + "-2");
        List<FeaturedImage> featuredImages = new ArrayList<>();
        if (result1 != null) {
            featuredImages.add(featuredImageRepository.save(new FeaturedImage(null, stay, (String) result1.get("url"))));
        }
        if (result2 != null) {
            featuredImages.add(featuredImageRepository.save(new FeaturedImage(null, stay, (String) result2.get("url"))));
        }

        stay.setAmenities(savedAmenities);
        stay.setFeaturedImages(featuredImages);
        return stay;
    }
}
