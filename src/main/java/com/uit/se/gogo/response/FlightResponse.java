package com.uit.se.gogo.response;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.se.gogo.entity.Airport;
import com.uit.se.gogo.entity.FeaturedImage;
import com.uit.se.gogo.entity.ServiceAmenity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlightResponse {
    private String id;

    @JsonIgnoreProperties("reviews")
    private AirlineResponse airline;

    @JsonProperty("min_base_fare")
    private Optional<BigDecimal> minBaseFare;

    @JsonProperty("departure_airport")
    private Airport departureAirport;
    
    @JsonProperty("arrival_airport")
    private Airport arrivalAirport;
    
    private String gate;
    private String timezone;
    
    @JsonProperty("departure_time")
    private Instant departureTime;

    @JsonProperty("arrival_time")
    private Instant arrivalTime;
    @JsonIgnoreProperties("flight")
    private List<SeatResponse> seats;
    private String name;
    @JsonIgnoreProperties("service")
    private List<ServiceAmenity> amenities;
    @JsonProperty("featured_images")
    @JsonIgnoreProperties("service")
    private List<FeaturedImage> featuredImages;
}
