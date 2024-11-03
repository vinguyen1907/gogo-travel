package com.uit.se.gogo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomBookingGuestInfoRequest {
    @JsonProperty("booking_id")
    @NotNull
    private String bookingId;
    @JsonProperty("first_name")
    @NotNull
    private String firstName;
    @JsonProperty("last_name")
    @NotNull
    private String lastName;
    @NotNull
    private String email;
    private String phone;
    @NotNull
    private String country;
}
