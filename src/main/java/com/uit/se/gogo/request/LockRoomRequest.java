package com.uit.se.gogo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LockRoomRequest {
    @JsonProperty("room_id")
    private String roomId;
}
