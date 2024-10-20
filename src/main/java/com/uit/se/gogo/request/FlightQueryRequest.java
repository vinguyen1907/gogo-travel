package com.uit.se.gogo.request;

import com.uit.se.gogo.enums.FlightOrderBy;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightQueryRequest {
    @NotNull(message = "Departure Location ID is required")
    private String departureLocationId;

    @NotNull(message = "Arrival Location ID is required")
    private String arrivalLocationId;

    private String seatClass;

    @Min(value = 0, message = "Min price must be at least 0")
    private Double minPrice;
    @Min(value = 0, message = "Max price must be at least 0")
    private Double maxPrice;

    @NotNull(message = "Rating is required")
    @Min(value = 0, message = "Rating must be at least 0")
    @Max(value = 5, message = "Rating cannot exceed 5")
    private Integer rating;

    private FlightOrderBy orderBy;

    @PositiveOrZero
    private Integer page = 0; // Default value
    @Positive
    private Integer pageSize = 10; // Default value
}
