package com.uit.se.gogo.request;

import com.uit.se.gogo.enums.BookingType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentCreationRequest {
    private String cardId;
    private String bookingId;
    private BookingType type;
}
