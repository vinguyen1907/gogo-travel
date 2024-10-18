package com.uit.se.gogo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.se.gogo.entity.StayFavorite;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StayFavoriteDTO {
    private String id;
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("stay_id")
    private String stayId;

    public StayFavoriteDTO(StayFavorite stayFavorite) {
        this.id = stayFavorite.getId();
        this.userId = stayFavorite.getUser().getId();
        this.stayId = stayFavorite.getStay().getId();
    }
}
