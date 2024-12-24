package com.uit.se.gogo.response;

import java.util.List;
import java.util.OptionalDouble;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private List<Review> reviews;

    private OptionalDouble rating;

    @JsonIgnoreProperties("airline") 
    private List<Policy> policies;
}
