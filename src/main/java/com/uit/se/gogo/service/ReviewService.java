package com.uit.se.gogo.service;

import com.uit.se.gogo.dto.ReviewDTO;
import com.uit.se.gogo.entity.Review;
import org.springframework.data.domain.Page;

public interface ReviewService {
    Page<ReviewDTO> findAllByServiceId(String serviceId, Integer page, Integer pageSize);

    Review create(Review review);
}
