package com.uit.se.gogo.controller;

import com.uit.se.gogo.request.CreateReviewRequest;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uit.se.gogo.dto.ReviewDTO;
import com.uit.se.gogo.entity.Review;
import com.uit.se.gogo.response.DataResponse;
import com.uit.se.gogo.response.PageDataResponse;
import com.uit.se.gogo.service.ReviewService;

import lombok.RequiredArgsConstructor;

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
    public ResponseEntity<DataResponse<ReviewDTO>> createReview(@RequestBody CreateReviewRequest request) throws BadRequestException {
        var saved = reviewService.create(request);
        return ResponseEntity.ok(new DataResponse<>(saved));
    }
}
