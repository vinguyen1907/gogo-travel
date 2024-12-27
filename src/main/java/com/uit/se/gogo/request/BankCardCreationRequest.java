package com.uit.se.gogo.request;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankCardCreationRequest {
    private String userId;

    private String number;
    private Date expiryDate;
    private String cvc;
    private String nameOnCard;
    private String region;
}
