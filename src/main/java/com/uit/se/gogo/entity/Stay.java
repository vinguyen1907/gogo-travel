package com.uit.se.gogo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.se.gogo.enums.StayType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Stay extends BaseService {
    private String address;
    @ManyToOne
    private Location location;
    private Double rating;
    private Long reviewCount = 0L;
    @JsonProperty("star_rating")
    private Integer starRating;
    @Enumerated(EnumType.STRING)
    @JsonProperty("stay_type")
    private StayType stayType;
    private String overview;
    private Double latitude;
    private Double longitude;
    @OneToMany(mappedBy = "stay")
    private List<StayAdvantage> advantages;
    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Review> reviews;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private User owner;
}
