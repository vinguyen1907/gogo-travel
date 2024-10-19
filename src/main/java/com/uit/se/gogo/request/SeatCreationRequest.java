package com.uit.se.gogo.request;

import java.math.BigDecimal;
import com.uit.se.gogo.enums.SeatClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatCreationRequest {
    private String number;
    private SeatClass seatClass;
    private BigDecimal baseFare;
    private BigDecimal serviceFee;
    private boolean available;
}
