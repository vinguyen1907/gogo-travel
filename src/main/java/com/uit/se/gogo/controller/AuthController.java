package com.uit.se.gogo.controller;

import com.uit.se.gogo.request.AuthenticationRequest;
import com.uit.se.gogo.request.AuthenticationResponse;
import com.uit.se.gogo.request.RegisterRequest;
import com.uit.se.gogo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authService.register(request));


    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        System.out.println("AUTHENTICATE");
        try {
            return ResponseEntity.ok(authService.authenticate(request));
        } catch (UsernameNotFoundException e) {
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(AuthenticationResponse.builder().message("This account is not found").build());
        } catch (BadCredentialsException e) {
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(AuthenticationResponse.builder().message("User not found or Incorrect password").build());
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
