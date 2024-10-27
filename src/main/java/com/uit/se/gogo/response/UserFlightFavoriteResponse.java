package com.uit.se.gogo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.se.gogo.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFlightFavoriteResponse {
    private User user;
    
    @JsonIgnoreProperties({"user", "seats"})
    @JsonProperty("flight_favorites")
    private List<FlightFavoriteResponse> flightFavorites;
}
