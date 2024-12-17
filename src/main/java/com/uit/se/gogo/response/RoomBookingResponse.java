package com.uit.se.gogo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.se.gogo.enums.RoomBookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomBookingResponse {
    @JsonProperty("lock_expiration")
    private LocalDateTime lockExpiration;
    private String id;
    private RoomBookingStatus status;
}
