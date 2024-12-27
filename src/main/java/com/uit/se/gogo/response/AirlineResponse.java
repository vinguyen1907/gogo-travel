package com.uit.se.gogo.response;

import java.util.List;
import java.util.OptionalDouble;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.se.gogo.entity.Policy;
import com.uit.se.gogo.entity.Review;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AirlineResponse {
    private String id;
    private String name;
    private String image;

    @JsonIgnoreProperties("service")
    private List<Review> reviews;

    @JsonProperty("review_count")
    private int reviewCount;

    private OptionalDouble rating;

    @JsonIgnoreProperties("airline") 
    private List<Policy> policies;
}
