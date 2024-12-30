package com.uit.se.gogo.repository;

import com.uit.se.gogo.entity.Stay;
import com.uit.se.gogo.enums.StayType;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface StayRepository extends JpaRepository<Stay, String> {
    @NonNull
    Optional<Stay> findById(@NonNull String id);

    @Query(
            value = """
        WITH booked_rooms AS (
            SELECT rb.room.id AS id,
                   room.baseFare AS baseFare,
                   room.serviceFee AS serviceFee,
                   room.discount AS discount
            FROM RoomBooking rb
               INNER JOIN Room room ON rb.room.id = room.id
            WHERE rb.checkinDate <= :checkoutDate
              AND rb.checkoutDate >= :checkinDate
        ),
        suitable_rooms AS (
            SELECT r.id AS sr_id,
                   r.stay.id AS sr_stayId,
                   r.baseFare AS sr_baseFare,
                   r.serviceFee AS sr_serviceFee,
                   r.discount AS sr_discount,
                   r.tax AS tax
            FROM Room r
            WHERE r.maxGuests >= :guests
              AND (:minPrice IS NULL OR r.baseFare + r.serviceFee - r.discount >= :minPrice)
              AND (:maxPrice IS NULL OR r.baseFare + r.serviceFee - r.discount <= :maxPrice)
              AND r.isAvailable = TRUE
              AND r.id NOT IN (
                  SELECT id
                  FROM booked_rooms
              )
            GROUP BY r.id, r.stay.id, r.baseFare, r.serviceFee, r.discount, r.tax
            HAVING COUNT(r.id) >= :rooms
        )
        SELECT s.id,
               s.address,
               s.location.id AS locationId,
               s.rating,
               s.starRating,
               s.stayType,
               s.overview,
               s.latitude,
               s.longitude,
               MIN((sr.sr_baseFare + sr.sr_serviceFee - sr.sr_discount) * (1 + sr.tax)) AS minPrice,
               COUNT(rvw.id) AS reviewAmount,
               AVG(rvw.rating) AS averageRating,
               COUNT(sa.id) AS amenity_count
        FROM Stay s
        LEFT JOIN Review rvw ON rvw.service.id = s.id
        LEFT JOIN suitable_rooms sr ON sr.sr_stayId = s.id
        LEFT JOIN ServiceAmenity sa ON sa.id = s.id
        WHERE s.location.id = :locationId
          AND (:rating IS NULL OR s.rating >= :rating)
          AND (:type IS NULL OR s.stayType = :type)
        GROUP BY s.id,
                 s.address,
                 s.location.id,
                 s.rating,
                 s.starRating,
                 s.stayType,
                 s.overview,
                 s.latitude,
                 s.longitude
        ORDER BY minPrice ASC
    """,
            countQuery = """
        SELECT COUNT(*)
        FROM Stay s
        WHERE s.location.id = :locationId
          AND (:rating IS NULL OR s.rating >= :rating)
          AND (:type IS NULL OR s.stayType = :type)
    """
    )
    Page<Object[]> search(
            LocalDate checkinDate,
            LocalDate checkoutDate,
            String locationId,
            Integer rooms,
            Integer guests,
            Double minPrice,
            Double maxPrice,
            Integer rating,
            StayType type,
            Pageable pageable);


}
