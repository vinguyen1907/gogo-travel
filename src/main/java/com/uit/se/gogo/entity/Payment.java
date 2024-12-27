package com.uit.se.gogo.entity;

import java.time.Instant;

import com.uit.se.gogo.enums.BookingType;
import com.uit.se.gogo.response.PaymentResponse;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @ManyToOne
    private User user;

    @ManyToOne
    private BankCard card;

    @OneToOne
    @JoinColumn(name = "booking_id", nullable=false)
    private BaseBooking booking;

    private Instant paymentTime;

    private BookingType type;

    public PaymentResponse toResponse() {
        return PaymentResponse.builder()
            .id(id)
            .user(user)
            .card(card)
            .booking(booking)
            .paymentTime(paymentTime)
            .type(type)
            .build();
    }
}
