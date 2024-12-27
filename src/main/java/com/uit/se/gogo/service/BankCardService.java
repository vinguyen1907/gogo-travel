package com.uit.se.gogo.service;

import java.util.List;

import com.uit.se.gogo.request.BankCardCreationRequest;
import com.uit.se.gogo.response.BankCardResponse;

public interface BankCardService {
    BankCardResponse createCard(BankCardCreationRequest request);
    BankCardResponse getById(String id);
    List<BankCardResponse> getByUserId(String userId);
    BankCardResponse deleteCard(String id);
}
