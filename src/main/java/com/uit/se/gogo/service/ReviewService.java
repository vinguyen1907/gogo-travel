package com.uit.se.gogo.service;

import com.uit.se.gogo.dto.ReviewDTO;
import com.uit.se.gogo.request.CreateReviewRequest;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;

public interface ReviewService {
    Page<ReviewDTO> findAllByServiceId(String serviceId, Integer page, Integer pageSize);

    ReviewDTO create(CreateReviewRequest request) throws BadRequestException;
}
