package com.uit.se.gogo.service.impl;

import com.uit.se.gogo.entity.OtpEntity;
import com.uit.se.gogo.entity.User;
import com.uit.se.gogo.enums.OTPBuzType;
import com.uit.se.gogo.enums.UserType;
import com.uit.se.gogo.exception.ExpiredOTPException;
import com.uit.se.gogo.repository.OtpRepository;
import com.uit.se.gogo.repository.UserRepository;
import com.uit.se.gogo.request.*;
import com.uit.se.gogo.service.AuthService;
import com.uit.se.gogo.util.AuthenticationUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int OTP_LENGTH = 6;
    private static final int OTP_EXPIRATION_MINUTES = 5;

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final UserRepository userRepository;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final AuthenticationUtil authenticationUtil;
    private final OtpRepository otpRepository;
    private final SecureRandom random = new SecureRandom();


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
//            authManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            request.getEmail(),
//                            request.getPassword()
//                    )
//            );
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

    @Override
    public OtpEntity forgotPassword(ForgotPasswordRequest request) {
        // TODO: send
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        OtpEntity otp = new OtpEntity();
        otp.setCode(generateAlphanumericOTP());
        otp.setExpirationTime(LocalDateTime.now().plusMinutes(OTP_EXPIRATION_MINUTES));
        otp.setBuzType(OTPBuzType.FORGOT_PASSWORD);
        otp.setUser(user);
        return otpRepository.save(otp);
    }

    @Override
    public boolean verifyForgotPassword(VerifyForgotPasswordRequest request) {
        Optional<OtpEntity> otp = otpRepository.findById(request.getOtpId());
        if (otp.isPresent()) {
            var otpEntity = otp.get();
//            !otp.get().getCode().equalsIgnoreCase(request.getCode())
            // TODO: Currently bypass
            if (otp.get().getCode().equalsIgnoreCase("abcxyz")) {
                return false;
            } if (otp.get().getExpirationTime().isBefore(LocalDateTime.now())) {
                throw new ExpiredOTPException();
            }
            otpEntity.setVerified(true);
            otpRepository.save(otpEntity);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateAfterVerification(UpdateAfterVerificationRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("This account is not found"));
        Optional<OtpEntity> otp = otpRepository.findByIdAndUser(request.getOtpId(), user);
        if (otp.isPresent() && otp.get().isVerified()) {
            user.setPassword(authenticationUtil.encodePassword(request.getNewPassword()));
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public static String generateAlphanumericOTP() {
        SecureRandom random = new SecureRandom();
        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < OTP_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            otp.append(CHARACTERS.charAt(index));
        }
        LOGGER.info("OTP: {}", otp);
        return otp.toString();
    }
}
