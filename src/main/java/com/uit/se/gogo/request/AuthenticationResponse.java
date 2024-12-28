package com.uit.se.gogo.request;

import com.uit.se.gogo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AuthenticationResponse {
    private String token;
    private String message;
    private User user;
}
