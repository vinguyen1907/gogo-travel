package com.uit.se.gogo.service.impl;

import com.uit.se.gogo.entity.User;
import com.uit.se.gogo.enums.UserType;
import com.uit.se.gogo.repository.UserRepository;
import com.uit.se.gogo.request.AuthenticationRequest;
import com.uit.se.gogo.request.AuthenticationResponse;
import com.uit.se.gogo.request.RegisterRequest;
import com.uit.se.gogo.service.AuthService;
import com.uit.se.gogo.util.AuthenticationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final AuthenticationUtil authenticationUtil;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .email(request.getEmail())
                .password(authenticationUtil.encodePassword(request.getPassword()))
                .fullName(request.getFirstName() + request.getLastName())
                .phoneNumber(request.getPhone())
                .userType(UserType.USER)
                .build();
        User savedUser = userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken, null, savedUser);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            var user = userRepository
                    .findByEmail(request.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("This account is not found"));
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .message("Authenticate successfully")
                    .user(user)
                    .build();
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException("This account is not found", e);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("User not found or Incorrect password", e);
        }
    }
}
