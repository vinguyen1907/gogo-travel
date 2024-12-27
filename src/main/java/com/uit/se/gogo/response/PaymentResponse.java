package com.uit.se.gogo.response;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.se.gogo.entity.BankCard;
import com.uit.se.gogo.entity.BaseBooking;
import com.uit.se.gogo.entity.User;
import com.uit.se.gogo.enums.BookingType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponse {
    private String id;
    
    @JsonIgnoreProperties("user")
    private BankCard card;
    
    private User user;

    @JsonIgnoreProperties("user")
    private BaseBooking booking;

    @JsonProperty("payment_time")
    private Instant paymentTime;

    private BookingType type;
}
