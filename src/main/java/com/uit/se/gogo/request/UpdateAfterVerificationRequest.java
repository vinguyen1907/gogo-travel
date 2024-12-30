package com.uit.se.gogo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAfterVerificationRequest {
    @JsonProperty("otp_id")
    private String otpId;
    @JsonProperty("new_password")
    private String newPassword;
    private String email;
}
