package com.uit.se.gogo.service;

import com.uit.se.gogo.dto.StayFavoriteDTO;
import com.uit.se.gogo.request.CreateStayFavoriteRequest;
import org.springframework.data.domain.Page;

public interface StayFavoriteService {

    Page<StayFavoriteDTO> findAllByUserId(String userId, Integer page, Integer size);

    StayFavoriteDTO insert(CreateStayFavoriteRequest request);

    boolean remove(String id);
}
