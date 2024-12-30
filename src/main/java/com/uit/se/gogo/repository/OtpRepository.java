package com.uit.se.gogo.repository;

import com.uit.se.gogo.entity.OtpEntity;
import com.uit.se.gogo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<OtpEntity, String> {
    Optional<OtpEntity> findByIdAndUser(String id, User user);
}
