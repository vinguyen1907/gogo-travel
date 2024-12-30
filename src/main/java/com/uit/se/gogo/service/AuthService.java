package com.uit.se.gogo.service;

import com.uit.se.gogo.entity.OtpEntity;
import com.uit.se.gogo.request.*;

public interface AuthService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    OtpEntity forgotPassword(ForgotPasswordRequest request);

    boolean verifyForgotPassword(VerifyForgotPasswordRequest request);

    boolean updateAfterVerification(UpdateAfterVerificationRequest request);
}
