package com.uit.se.gogo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uit.se.gogo.request.SendOTPRequest;
import com.uit.se.gogo.response.DataResponse;
import com.uit.se.gogo.response.EmailResponse;
import com.uit.se.gogo.service.EmailService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class EmailController {
    EmailService emailService;

    @GetMapping("/email")
    public String test() {
        return "HEllo world";
    }
    

    @PostMapping("/email/otp")
    public DataResponse<EmailResponse> sendEmail(@RequestBody SendOTPRequest request) {
        return DataResponse.<EmailResponse>builder()
                .data(emailService.resetPasswordOTP(request))
                .build();
    }
}
