package com.uit.se.gogo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRoomRequest {
    @JsonProperty("owner_id")
    @NotBlank
    private String ownerId;
    @NotBlank
    private String name;
    @JsonProperty("stay_id")
    @NotBlank
    private String stayId;
    @JsonProperty("base_fare")
    @Positive
    private Double baseFare;
    @PositiveOrZero
    private Double discount;
    @PositiveOrZero
    private Double tax; // rate
    @JsonProperty("service_fee")
    @PositiveOrZero
    private Double serviceFee;
    private String type;
    @JsonProperty("max_guests")
    @Positive
    private Integer maxGuests;
    @JsonProperty("image_url")
    @NotBlank
    private String imageUrl;
}
