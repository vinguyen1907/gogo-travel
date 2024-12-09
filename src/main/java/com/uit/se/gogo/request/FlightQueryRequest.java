package com.uit.se.gogo.request;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.se.gogo.enums.FlightOrderBy;
import com.uit.se.gogo.enums.SeatClass;

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
    @JsonProperty("departure_location_id")
    private String departureLocationId;

    @NotNull(message = "Arrival Location ID is required")
    @JsonProperty("arrival_location_id")
    private String arrivalLocationId;

    @NotNull
    @JsonProperty("departure_time_from")
    private Date departureTimeFrom;
    
    @NotNull
    @JsonProperty("departure_time_to")
    private Date departureTimeTo;

    @JsonProperty("return_time_from")
    private Date returnTimeFrom;
    @JsonProperty("return_time_to")
    private Date returnTimeTo;

    @JsonProperty("seat_classes")
    private List<SeatClass> seatClasses;

    @Min(value = 0, message = "Min price must be at least 0")
    @JsonProperty("min_price")
    private Double minPrice;
    @Min(value = 0, message = "Max price must be at least 0")
    @JsonProperty("max_price")
    private Double maxPrice;

    @JsonProperty("order_by")
    @Builder.Default
    private FlightOrderBy orderBy = FlightOrderBy.NONE;
    
    public boolean isRoundTrip() {
        return returnTimeFrom != null && returnTimeTo != null;
    }

    @Positive
    @Builder.Default
    @JsonProperty("passenger_count")
    private Integer passengerCount = 1; 
    
    @PositiveOrZero
    @Builder.Default
    private Integer page = 0; 
    
    @Positive
    @Builder.Default
    @JsonProperty("page_size")
    private Integer pageSize = 10;
}
