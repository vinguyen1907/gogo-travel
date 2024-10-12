package com.uit.se.gogo.service.impl;

import com.uit.se.gogo.entity.Stay;
import com.uit.se.gogo.repository.StayRepository;
import com.uit.se.gogo.request.SearchStayRequest;
import com.uit.se.gogo.service.StayService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StayServiceImpl implements StayService {
    private final StayRepository stayRepository;

    @Override
    public Stay findById(String id) {
        var stay = stayRepository.findById(id);
        return stay.orElseThrow(() -> new EntityNotFoundException("Stay not found"));
    }

    @Override
    public List<Stay> search(SearchStayRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());
        return stayRepository.search(
//                request.getCheckinDate(),
//                request.getCheckoutDate(),
                request.getLocationId(),
//                request.getRooms(),
//                request.getGuests(),
//                request.getMinPrice(),
//                request.getMaxPrice(),
                request.getRating(),
                request.getType(),
                pageable
        );
    }
}
