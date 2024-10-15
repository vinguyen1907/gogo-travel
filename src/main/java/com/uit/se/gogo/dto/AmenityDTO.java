package com.uit.se.gogo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.se.gogo.entity.Amenity;
import com.uit.se.gogo.enums.AmenityIcon;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AmenityDTO {
    private String id;
    private String name;
    private AmenityIcon icon;
    @JsonProperty("is_featured")
    private Boolean isFeatured;

    public AmenityDTO(Amenity amenity) {
        this.id = amenity.getId();
        this.name = amenity.getName();
        this.icon = amenity.getIcon();
        this.isFeatured = amenity.getIsFeatured();
    }
}
