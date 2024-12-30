package com.uit.se.gogo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.se.gogo.enums.StayType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminCreateStayRequest {
    private String name;
    @NotBlank
    private String address;
    @JsonProperty("location_id")
    @NotBlank
    private String locationId;
    @JsonProperty("star_rating")
    @Positive
    @Max(5)
    private Integer starRating;
    @JsonProperty("stay_type")
    @NotNull
    private StayType stayType;
    @NotBlank
    private String overview;
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;
    private List<String> amenities;
}
