package com.uit.se.gogo.entity;

import java.util.Date;

import com.uit.se.gogo.request.BankCardCreationRequest;
import com.uit.se.gogo.response.BankCardResponse;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class BankCard {
    @Id
    private String id;

    @ManyToOne
    private User user;

    private String number;
    private Date expiryDate;
    private String cvc;
    private String nameOnCard;
    private String region;

    @Builder.Default
    private boolean deleted = false;

    public static BankCard fromCreationRequest(BankCardCreationRequest request) {
        return BankCard.builder()
            .number(request.getNumber())
            .expiryDate(request.getExpiryDate())
            .cvc(request.getCvc())
            .nameOnCard(request.getNameOnCard())
            .region(request.getRegion())
            .build();
    }

    public BankCardResponse toResponse() {
        return BankCardResponse.builder()
            .id(id)
            .user(user)
            .number(number)
            .expiryDate(expiryDate)
            .cvc(cvc)
            .nameOnCard(nameOnCard)
            .region(region)
            .deleted(deleted)
            .build();
    }
}
