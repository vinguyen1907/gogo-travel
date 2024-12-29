package com.uit.se.gogo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.se.gogo.entity.Review;
import com.uit.se.gogo.entity.User;
import com.uit.se.gogo.enums.ReviewServiceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private String id;
    @JsonProperty("service_id")
    private String serviceId;
    @JsonProperty("user_id")
    private String userId;
    private Integer rating;
    private String description;
    @JsonProperty("service_type")
    private ReviewServiceType serviceType;

    public ReviewDTO(Review review) {
        this.id = review.getId();
        this.serviceId = review.getService().getId();
        this.userId = review.getUser().getId();
        this.rating = review.getRating();
        this.description = review.getDescription();
        this.serviceType = review.getServiceType();
    }
}
