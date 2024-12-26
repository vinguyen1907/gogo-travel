package com.uit.se.gogo.repository;

import com.uit.se.gogo.entity.FeaturedImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeaturedImageRepository extends JpaRepository<FeaturedImage, String> {
    List<FeaturedImage> findAllByServiceId(String serviceId);
}
