package com.uit.se.gogo.repository;

import com.uit.se.gogo.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {

    Page<Review> findByServiceId(String serviceId, Pageable pageable);

    Optional<Review> findByServiceIdAndUserId(String serviceId, String userId);
}
