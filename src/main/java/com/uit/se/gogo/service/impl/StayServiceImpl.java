package com.uit.se.gogo.service.impl;

import com.uit.se.gogo.entity.Stay;
import com.uit.se.gogo.repository.StayRepository;
import com.uit.se.gogo.service.StayService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StayServiceImpl implements StayService {
    private final StayRepository stayRepository;

    @Override
    public Stay findById(String id) {
        return stayRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Stay not found"));
    }
}
