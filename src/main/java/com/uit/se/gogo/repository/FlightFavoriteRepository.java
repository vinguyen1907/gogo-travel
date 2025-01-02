package com.uit.se.gogo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uit.se.gogo.entity.FlightFavorite;

@Repository
public interface FlightFavoriteRepository extends JpaRepository<FlightFavorite, String> {
    public List<FlightFavorite> findAllByUserId(String userId);
    public Optional<FlightFavorite> findByUserIdAndOutboundFlightIdAndReturnFlightId(String userId, String outboundId, String returnId);
}
