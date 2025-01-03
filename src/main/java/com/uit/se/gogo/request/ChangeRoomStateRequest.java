package com.uit.se.gogo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.se.gogo.enums.RoomBookingStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeRoomStateRequest {
    @JsonProperty("booking_id")
    @NotNull
    private String bookingId;
    @JsonProperty("new_status")
    @NotNull
    private RoomBookingStatus newStatus;
}
