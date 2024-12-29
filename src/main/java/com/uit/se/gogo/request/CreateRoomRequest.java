package com.uit.se.gogo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRoomRequest {
    @JsonProperty("owner_id")
    private String ownerId;
    private String name;
    @JsonProperty("stay_id")
    private String stayId;
    @JsonProperty("base_fare")
    private Double baseFare;
    private Double discount;
    private Double tax; // rate
    @JsonProperty("service_fee")
    private Double serviceFee;
    private String type;
    @JsonProperty("max_guests")
    private Integer maxGuests;
    @JsonProperty("image_url")
    private String imageUrl;
}
