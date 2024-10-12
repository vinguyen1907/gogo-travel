package com.uit.se.gogo.repository;

import com.uit.se.gogo.entity.Stay;
import com.uit.se.gogo.enums.StayType;
import lombok.NonNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface StayRepository extends JpaRepository<Stay, String> {
    @NonNull
    Optional<Stay> findById(@NonNull String id);

    @Query("SELECT s FROM Stay s " +
            "WHERE s.location.id = :locationId " +
            "AND (:rating IS NULL OR s.rating >= :rating) " +
            "AND s.stayType = :type")
    List<Stay> search(
//            Date checkinDate,
//            Date checkoutDate,
            String locationId,
//            Integer rooms,
//            Integer guests,
//            Double minPrice,
//            Double maxPrice,
            Integer rating,
            StayType type,
            Pageable pageable);
}
