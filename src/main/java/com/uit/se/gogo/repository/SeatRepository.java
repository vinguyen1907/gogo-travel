package com.uit.se.gogo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uit.se.gogo.entity.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, String> {
    public List<Seat> findAllByFlightId(String id);
    public List<Seat> findByFlightIdAndAvailable(String flightId, boolean isAvailable);
}
