package com.uit.se.gogo.response;

import java.util.Date;

import com.uit.se.gogo.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankCardResponse {
    private String id;

    private User user;

    private String number;
    private Date expiryDate;
    private String cvc;
    private String nameOnCard;
    private String region;

    private boolean deleted;
}
