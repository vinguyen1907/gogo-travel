package com.uit.se.gogo.service.impl;

import com.uit.se.gogo.entity.Amenity;
import com.uit.se.gogo.repository.AmenityRepository;
import com.uit.se.gogo.service.AmenityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AmenityServiceImpl implements AmenityService {
    private final AmenityRepository amenityRepository;

    @Override
    public List<Amenity> findAll(boolean isFeatured) {
        return amenityRepository.findAllByIsFeatured(isFeatured);
    }

    @Override
    public List<Amenity> findAllByIds(List<String> ids) {
        return amenityRepository.findAllByIdIn(ids);
    }
}
