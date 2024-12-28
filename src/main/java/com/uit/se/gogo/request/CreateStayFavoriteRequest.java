package com.uit.se.gogo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateStayFavoriteRequest {
    @JsonProperty("stay_id")
    @NotNull
    private String stayId;
    @JsonProperty("user_id")
    @NotNull
    private String userId;
}
