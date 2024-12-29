package com.uit.se.gogo.service.impl;

import com.uit.se.gogo.dto.ReviewDTO;
import com.uit.se.gogo.entity.BaseService;
import com.uit.se.gogo.entity.Review;
import com.uit.se.gogo.entity.Stay;
import com.uit.se.gogo.entity.User;
import com.uit.se.gogo.enums.ReviewServiceType;
import com.uit.se.gogo.repository.*;
import com.uit.se.gogo.request.CreateReviewRequest;
import com.uit.se.gogo.service.ReviewService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ServiceRepository serviceRepository;
    private final StayRepository stayRepository;
    private final FlightRepository flightRepositoryRepository;

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
    public ReviewDTO create(CreateReviewRequest request) throws BadRequestException {
        var currentReview = reviewRepository.findByServiceIdAndUserId(request.getServiceId(), request.getUserId());
        if (currentReview.isPresent()) {
            throw new BadRequestException("You have already added a review");
        }
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        BaseService service;
        if (request.getServiceType() == ReviewServiceType.STAY) {
            Stay stay = stayRepository.findById(request.getServiceId())
                    .orElseThrow(() -> new EntityNotFoundException("Stay not found"));
            Double currentRating = stay.getRating();
            long currentCount = stay.getReviewCount() == null ? 0 : stay.getReviewCount();
            long newCount = currentCount + 1;
            Double newRating = currentRating == null ? request.getRating() : (currentRating * currentCount + request.getRating()) / newCount;
            stay.setRating(newRating);
            stay.setReviewCount(newCount);
            stayRepository.save(stay);
            service = stay;
        } else {
            service = serviceRepository.findById(request.getServiceId())
                    .orElseThrow(() -> new EntityNotFoundException("Service not found"));
        }
        Review review = new Review();
        review.setUser(user);
        review.setService(service);
        review.setRating(request.getRating());
        review.setDescription(request.getDescription());
        review.setServiceType(request.getServiceType());
        reviewRepository.save(review);

        return new ReviewDTO(review);
    }

}
