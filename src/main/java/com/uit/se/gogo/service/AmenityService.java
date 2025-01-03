package com.uit.se.gogo.service;

import com.uit.se.gogo.entity.Amenity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AmenityService {
    List<Amenity> findAll(boolean isFeatured);

    List<Amenity> findAllByIds(List<String> ids);
}
