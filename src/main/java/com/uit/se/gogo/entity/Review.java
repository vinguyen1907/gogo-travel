package com.uit.se.gogo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    private BaseService service;
    private String userId;
    private Integer rating;
    private String description;
}
