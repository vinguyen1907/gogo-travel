package com.uit.se.gogo.repository;

import com.uit.se.gogo.entity.Location;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, String> {

    @NonNull
    List<Location> findAll();
}
