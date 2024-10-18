package com.uit.se.gogo.service.impl;

import com.uit.se.gogo.dto.ReviewDTO;
import com.uit.se.gogo.entity.Review;
import com.uit.se.gogo.repository.ReviewRepository;
import com.uit.se.gogo.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    @Override
    public Page<ReviewDTO> findAllByServiceId(String serviceId, Integer page, Integer pageSize) {
        var pageable = PageRequest.of(page, pageSize);
        Page<Review> reviews = reviewRepository.findByServiceId(serviceId, pageable);
        List<ReviewDTO> reviewDTOs = reviews.getContent().stream()
                .map(ReviewDTO::new)
                .toList();
        return new PageImpl<>(reviewDTOs, reviews.getPageable(), reviews.getTotalElements());
    }

    @Override
    public Review create(Review review) {
        return reviewRepository.save(review);
    }
}
