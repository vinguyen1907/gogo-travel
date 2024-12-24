package com.uit.se.gogo.entity;

import java.util.List;
import java.util.Objects;
import java.util.OptionalDouble;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Airline extends BaseService{
    @Column(nullable = false)
    private String image;

    @OneToMany(mappedBy = "airline", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("airline")
    private List<Policy> policies;
    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Review> reviews;

    
    public OptionalDouble getRating() {
        return reviews.stream()
            .map(Review::getRating)
            .filter(Objects::nonNull) // Avoid null values
            .mapToInt(Integer::intValue)
            .average();
    }
}