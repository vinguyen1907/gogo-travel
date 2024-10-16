package com.uit.se.gogo.repository;

import com.uit.se.gogo.entity.StayFavorite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StayFavoriteRepository extends JpaRepository<StayFavorite, String> {
    Page<StayFavorite> findAllByUserId(String userId, Pageable pageable);
}
