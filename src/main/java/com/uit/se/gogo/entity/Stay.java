package com.uit.se.gogo.entity;

import com.uit.se.gogo.enums.StayType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Stay extends BaseService {
    private String address;
    @ManyToOne
    private Location location;
    private Double rating;
    private Integer starRating;
    @Enumerated
    private StayType stayType;
    private String overview;
    private Double latitude;
    private Double longitude;
    @OneToMany
    private List<StayAdvantage> advantages;
}
