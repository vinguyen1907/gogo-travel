package com.uit.se.gogo.entity;

import com.uit.se.gogo.enums.OTPBuzType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtpEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String code;
    private LocalDateTime expirationTime;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    private boolean isVerified = false;
    @Enumerated(EnumType.STRING)
    private OTPBuzType buzType;
}
