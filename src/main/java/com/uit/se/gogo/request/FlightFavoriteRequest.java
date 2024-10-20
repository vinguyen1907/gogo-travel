package com.uit.se.gogo.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightFavoriteRequest {
    @NotNull(message = "Must provide user Id")
    private String userId;

    @NotNull(message = "Must provide flight Id")
    private String outboundFlightId;
    private String returnFlightId;
}
