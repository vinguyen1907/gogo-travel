package com.uit.se.gogo.repository;

import com.uit.se.gogo.entity.Stay;
import com.uit.se.gogo.enums.StayType;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface StayRepository extends JpaRepository<Stay, String> {
    @NonNull
    Optional<Stay> findById(@NonNull String id);

    @Query(
            "SELECT s " +
            "FROM Stay s " +
            "WHERE s.location.id = :locationId " +
            "AND (:rating IS NULL OR s.rating >= :rating) " +
            "AND s.stayType = :type " +
            "AND s.id IN ( " +
                "SELECT r.stay.id AS stay_id " +
                "FROM Room AS r " +
                "WHERE r.maxGuests >= :guests " +
                "AND r.baseFare + r.serviceFee - r.discount BETWEEN :minPrice AND :maxPrice " +
                "AND r.isAvailable = TRUE " +
                "AND r.id NOT IN (" +
                    "SELECT r.id " +
                    "FROM Room AS r " +
                    "JOIN RoomBooking AS rb " +
                    "ON r.id = rb.room.id " +
                    "WHERE rb.checkinDate <= :checkoutDate " +
                    "AND rb.checkoutDate >= :checkinDate" +
                ") " +
                "GROUP BY r.stay.id " +
                "HAVING COUNT(r.id) >= :rooms" +
            ")"
    )
    Page<Stay> search(
            Date checkinDate,
            Date checkoutDate,
            String locationId,
            Integer rooms,
            Integer guests,
            Double minPrice,
            Double maxPrice,
            Integer rating,
            StayType type,
            Pageable pageable);
}
