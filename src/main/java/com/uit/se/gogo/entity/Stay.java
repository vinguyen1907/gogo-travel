package com.uit.se.gogo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.se.gogo.enums.StayType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Stay extends BaseService {
    private String name;
    private String address;
    @ManyToOne
    private Location location;
    private Double rating;
    @JsonProperty("star_rating")
    private Integer starRating;
    @Enumerated
    @JsonProperty("stay_type")
    private StayType stayType;
    private String overview;
    private Double latitude;
    private Double longitude;
    @OneToMany
    private List<StayAdvantage> advantages;
}
