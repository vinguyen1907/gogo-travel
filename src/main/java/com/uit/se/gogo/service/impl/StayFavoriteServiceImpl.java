package com.uit.se.gogo.service.impl;

import com.uit.se.gogo.dto.StayFavoriteDTO;
import com.uit.se.gogo.entity.StayFavorite;
import com.uit.se.gogo.repository.StayFavoriteRepository;
import com.uit.se.gogo.service.StayFavoriteService;
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

    @Override
    public StayFavoriteDTO save(StayFavorite stayFavorite) {
        return new StayFavoriteDTO(stayFavoriteRepository.save(stayFavorite));
    }

    @Override
    public Page<StayFavoriteDTO> findAllByUserId(String userId, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        var stayFavoritePage = stayFavoriteRepository.findAllByUserId(userId, pageable);
        var stayFavoriteDTOs = stayFavoritePage.getContent().stream().map(StayFavoriteDTO::new).toList();
        return new PageImpl<>(stayFavoriteDTOs, stayFavoritePage.getPageable(), stayFavoritePage.getTotalElements());
    }
}
