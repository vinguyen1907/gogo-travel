package com.uit.se.gogo.repository;

import com.uit.se.gogo.entity.ServiceAmenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceAmenityRepository extends JpaRepository<ServiceAmenity, String> {
}
