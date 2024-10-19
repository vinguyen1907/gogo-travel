package com.uit.se.gogo.controller;

import com.uit.se.gogo.dto.StayFavoriteDTO;
import com.uit.se.gogo.entity.StayFavorite;
import com.uit.se.gogo.response.DataResponse;
import com.uit.se.gogo.response.PageDataResponse;
import com.uit.se.gogo.service.StayFavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/favorites/stays")
@RequiredArgsConstructor
public class StayFavoriteController {
    private final StayFavoriteService stayFavoriteService;

    @PostMapping
    public ResponseEntity<DataResponse<StayFavoriteDTO>> createStayFavorite(@RequestBody StayFavorite stayFavorite) {
        return ResponseEntity.ok(new DataResponse<>(stayFavoriteService.save(stayFavorite)));
    }

    @GetMapping
    public ResponseEntity<PageDataResponse<StayFavoriteDTO>> getAllStayFavoritesByUser(
            @RequestParam("user_id") String userId,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size
    ) {
        return ResponseEntity.ok(new PageDataResponse<>(stayFavoriteService.findAllByUserId(userId, page, size)));
    }
}