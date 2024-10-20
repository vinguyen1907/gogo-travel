package com.uit.se.gogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uit.se.gogo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    
}
