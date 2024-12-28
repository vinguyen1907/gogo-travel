package com.uit.se.gogo.repository;

import com.uit.se.gogo.entity.BaseService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<BaseService, String> {
}
