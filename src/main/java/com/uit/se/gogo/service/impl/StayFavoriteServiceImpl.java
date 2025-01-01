package com.uit.se.gogo.service.impl;

import com.uit.se.gogo.dto.StayFavoriteDTO;
import com.uit.se.gogo.entity.Stay;
import com.uit.se.gogo.entity.StayFavorite;
import com.uit.se.gogo.entity.User;
import com.uit.se.gogo.repository.StayFavoriteRepository;
import com.uit.se.gogo.repository.StayRepository;
import com.uit.se.gogo.repository.UserRepository;
import com.uit.se.gogo.request.CreateStayFavoriteRequest;
import com.uit.se.gogo.service.StayFavoriteService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StayFavoriteServiceImpl implements StayFavoriteService {
    private final StayFavoriteRepository stayFavoriteRepository;
    private final UserRepository userRepository;
    private final StayRepository stayRepository;

    @Override
    public Page<StayFavoriteDTO> findAllByUserId(String userId, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<StayFavorite> stayFavoritePage = stayFavoriteRepository.findAllByUserId(userId, pageable);
        var stayFavoriteDTOs = stayFavoritePage.getContent().stream().map(StayFavoriteDTO::new).toList();
        return new PageImpl<>(stayFavoriteDTOs, stayFavoritePage.getPageable(), stayFavoritePage.getTotalElements());
    }

    @Override
    public StayFavoriteDTO insert(CreateStayFavoriteRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Stay stay = stayRepository.findById(request.getStayId())
                .orElseThrow(() -> new EntityNotFoundException("Stay not found"));
        StayFavorite stayFavorite = new StayFavorite();
        stayFavorite.setUser(user);
        stayFavorite.setStay(stay);
        return new StayFavoriteDTO(stayFavoriteRepository.save(stayFavorite));
    }
}
