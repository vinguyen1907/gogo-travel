package com.uit.se.gogo.controller;

import com.uit.se.gogo.dto.ReviewDTO;
import com.uit.se.gogo.entity.Review;
import com.uit.se.gogo.response.DataResponse;
import com.uit.se.gogo.response.PageDataResponse;
import com.uit.se.gogo.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<PageDataResponse<ReviewDTO>> getReviews(@RequestParam("service_id") String serviceId,
                                                                 @RequestParam(defaultValue = "0") Integer page,
                                                                 @RequestParam(name = "page_size", defaultValue = "10") Integer pageSize) {
        var reviews = reviewService.findAllByServiceId(serviceId, page, pageSize);
        return ResponseEntity.ok(new PageDataResponse<>(reviews));
    }

    @PostMapping
    public ResponseEntity<DataResponse<ReviewDTO>> createReview(@RequestBody Review review) {
        var saved = reviewService.create(review);
        return ResponseEntity.ok(new DataResponse<>(new ReviewDTO(saved)));
    }
}
