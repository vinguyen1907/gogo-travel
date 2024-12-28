package com.uit.se.gogo.service.impl;

import com.uit.se.gogo.dto.ReviewDTO;
import com.uit.se.gogo.entity.BaseService;
import com.uit.se.gogo.entity.Review;
import com.uit.se.gogo.entity.User;
import com.uit.se.gogo.repository.ReviewRepository;
import com.uit.se.gogo.repository.ServiceRepository;
import com.uit.se.gogo.repository.UserRepository;
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
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        BaseService service = serviceRepository.findById(request.getServiceId())
                .orElseThrow(() -> new EntityNotFoundException("Service not found"));
        var currentReview = reviewRepository.findByServiceIdAndUserId(request.getServiceId(), request.getUserId());
        if (currentReview.isPresent()) {
            throw new BadRequestException("Service already exists");
        }
        Review review = new Review();
        review.setUser(user);
        review.setService(service);
        review.setRating(request.getRating());
        review.setDescription(request.getDescription());
        review.setServiceType(request.getServiceType());
        return new ReviewDTO(reviewRepository.save(review));
    }

}
