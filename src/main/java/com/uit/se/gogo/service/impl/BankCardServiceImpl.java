package com.uit.se.gogo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uit.se.gogo.entity.BankCard;
import com.uit.se.gogo.entity.User;
import com.uit.se.gogo.exception.CommonException;
import com.uit.se.gogo.repository.BankCardRepository;
import com.uit.se.gogo.repository.UserRepository;
import com.uit.se.gogo.request.BankCardCreationRequest;
import com.uit.se.gogo.response.BankCardResponse;
import com.uit.se.gogo.service.BankCardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BankCardServiceImpl implements BankCardService{
    private final BankCardRepository repository;
    private final UserRepository userRepository;

    @Override
    public BankCardResponse createCard(BankCardCreationRequest request) {
        BankCard card = BankCard.fromCreationRequest(request);
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new CommonException("User not found"));
        card.setUser(user);
        card = repository.save(card);
        return card.toResponse();
    }

    @Override
    public BankCardResponse getById(String id) {
        BankCard card = repository.findById(id).orElseThrow(() -> new CommonException("Card not found"));
        return card.toResponse();
    }

    @Override
    public List<BankCardResponse> getByUserId(String userId) {
        return repository.findAllByUserId(userId)
            .stream().map(BankCard::toResponse).toList();
    }

    @Override
    public BankCardResponse deleteCard(String id) {
        BankCard card = repository.findById(id).orElseThrow(() -> new CommonException("Card not found"));
        card.setDeleted(true);
        card = repository.save(card);
        return card.toResponse();
    }
    
}
