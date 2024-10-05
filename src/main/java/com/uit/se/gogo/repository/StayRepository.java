package com.uit.se.gogo.repository;

import com.uit.se.gogo.entity.Stay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StayRepository extends JpaRepository<Stay, String> {
    Optional<Stay> findById( String id);
}
