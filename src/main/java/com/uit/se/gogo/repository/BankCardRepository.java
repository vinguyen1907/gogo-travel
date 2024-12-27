package com.uit.se.gogo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uit.se.gogo.entity.BankCard;

public interface BankCardRepository extends JpaRepository<BankCard, String>{
    List<BankCard> findAllByUserId(String userId);
}
