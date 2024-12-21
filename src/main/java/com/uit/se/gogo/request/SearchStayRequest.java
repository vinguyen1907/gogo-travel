package com.uit.se.gogo.request;

import java.time.LocalDate;
import java.util.Date;

import com.uit.se.gogo.enums.StayType;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchStayRequest {

    @NotNull(message = "Location ID is required")
    private String locationId;

    @NotNull(message = "Check-in date is required")
    private LocalDate checkinDate;

    @NotNull(message = "Check-out date is required")
    private LocalDate checkoutDate;

    @NotNull(message = "Rooms is required")
    @Min(value = 1, message = "Rooms must be at least 1")
    private Integer rooms;

    @NotNull(message = "Guests is required")
    @Min(value = 1, message = "Guests must be at least 1")
    private Integer guests;

    @NotNull(message = "Min price is required")
    @Min(value = 0, message = "Min price must be at least 0")
    private Double minPrice;

    @NotNull(message = "Max price is required")
    @Min(value = 0, message = "Max price must be at least 0")
    private Double maxPrice;

    @NotNull(message = "Rating is required")
    @Min(value = 0, message = "Rating must be at least 0")
    @Max(value = 5, message = "Rating cannot exceed 5")
    private Integer rating;

    @NotNull(message = "Stay type is required")
    private StayType type;

    @PositiveOrZero
    @Builder.Default
    private Integer page = 0; // Default value

    @Positive
    @Builder.Default
    private Integer pageSize = 10; // Default value
}
