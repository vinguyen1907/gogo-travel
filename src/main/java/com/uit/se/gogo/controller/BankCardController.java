package com.uit.se.gogo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.uit.se.gogo.service.BankCardService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uit.se.gogo.request.BankCardCreationRequest;
import com.uit.se.gogo.response.BankCardResponse;
import com.uit.se.gogo.response.DataResponse;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bank-card")
public class BankCardController {
    private final BankCardService service;

    @PostMapping
    public DataResponse<BankCardResponse> createCard(@RequestBody BankCardCreationRequest request) {
        return DataResponse.<BankCardResponse>builder()
            .data(service.createCard(request))
            .build();
    }

    @GetMapping("/{id}")
    public DataResponse<BankCardResponse> getById(@PathVariable String id) {
        return DataResponse.<BankCardResponse>builder()
            .data(service.getById(id))
            .build();
    }

    @GetMapping("/user/{userId}")
    public DataResponse<List<BankCardResponse>> getByUserId(@PathVariable String userId) {
        return DataResponse.<List<BankCardResponse>>builder()
            .data(service.getByUserId(userId))
            .build();
    }

    @DeleteMapping("{id}")
    public DataResponse<BankCardResponse> delete(@PathVariable String id) {
        return DataResponse.<BankCardResponse>builder()
            .data(service.deleteCard(id))
            .build();
    }
}
