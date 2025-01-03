package com.uit.se.gogo.repository;

import com.uit.se.gogo.entity.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmenityRepository extends JpaRepository<Amenity, String> {
    List<Amenity> findAllByIsFeatured(boolean isFeatured);

    List<Amenity> findAllByIdIn(List<String> ids);
}
