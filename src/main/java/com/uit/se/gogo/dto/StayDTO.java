package com.uit.se.gogo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.se.gogo.entity.*;
import com.uit.se.gogo.enums.StayType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StayDTO {
    private String id;
    private String name;
    private Set<AmenityDTO> amenities;
    private String address;
    private Location location;
    private Double rating;
    @JsonProperty("star_rating")
    private Integer starRating;
    @JsonProperty("stay_type")
    private StayType stayType;
    private String overview;
    private Double latitude;
    private Double longitude;
    private List<StayAdvantage> advantages;
    @JsonProperty("featured_images")
    private List<FeaturedImage> featuredImages;
    @JsonProperty("min_price")
    private Double minPrice;
    @JsonProperty("review_count")
    private Long reviewAmount;
    @JsonProperty("average_rating")
    private Double averageRating;
    @JsonProperty("amenity_count")
    private Long amenityCount;

    public StayDTO(Stay stay) {
        this.id = stay.getId();
        this.name = stay.getName();
        this.amenities = new HashSet<>();
        stay.getAmenities().forEach((serviceAmenity) -> amenities.add(
                new AmenityDTO(serviceAmenity.getAmenity())));
        this.address = stay.getAddress();
        this.location = stay.getLocation();
        this.rating = stay.getRating();
        this.reviewAmount = stay.getReviewCount();
        this.averageRating = stay.getRating();
        this.starRating = stay.getStarRating();
        this.stayType = stay.getStayType();
        this.overview = stay.getOverview();
        this.latitude = stay.getLatitude();
        this.longitude = stay.getLongitude();
        this.advantages = stay.getAdvantages();
        this.featuredImages = stay.getFeaturedImages();
        this.amenityCount = (long) stay.getAmenities().size();
    }
}
