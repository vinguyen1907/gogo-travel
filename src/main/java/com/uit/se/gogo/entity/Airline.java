// package com.uit.se.gogo.entity;

// import java.util.List;

// import jakarta.persistence.CascadeType;
// import jakarta.persistence.Entity;
// import jakarta.persistence.FetchType;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.OneToMany;
// import lombok.Data;

// @Data
// @Entity
// public class Airline {
//     @Id
//     @GeneratedValue(strategy = GenerationType.UUID)
//     private String id;

//     private String name;
//     private String image;

//     @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//     private List<Review> reviews;

//     public double getRatings() {
//         return reviews.stream()
//                       .mapToInt(Review::getRating)
//                       .average()
//                       .orElse(0.0); // Default to 0 if no reviews
//     }

//     public int getReviewCount() {
//         return reviews.size();
//     }
// }