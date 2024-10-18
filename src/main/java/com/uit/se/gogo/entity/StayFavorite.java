package com.uit.se.gogo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StayFavorite {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "favorite_stay")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    private Stay stay;
}
