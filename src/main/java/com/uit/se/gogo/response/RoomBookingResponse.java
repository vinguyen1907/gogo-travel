package com.uit.se.gogo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
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
}
