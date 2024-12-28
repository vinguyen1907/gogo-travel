package com.uit.se.gogo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.se.gogo.enums.ReviewServiceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReviewRequest {
    @JsonProperty("service_id")
    @NotBlank
    private String serviceId;
    @JsonProperty("user_id")
    @NotBlank
    private String userId;
    @NotNull
    @Positive
    private Integer rating;
    @NotBlank
    private String description;
    @JsonProperty("service_type")
    @NotNull
    private ReviewServiceType serviceType;
}
