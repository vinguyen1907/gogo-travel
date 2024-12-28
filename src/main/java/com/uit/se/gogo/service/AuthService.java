package com.uit.se.gogo.service;

import com.uit.se.gogo.request.AuthenticationRequest;
import com.uit.se.gogo.request.AuthenticationResponse;
import com.uit.se.gogo.request.RegisterRequest;

public interface AuthService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
