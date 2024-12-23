package com.uit.se.gogo.service;

import java.time.LocalDateTime;

public interface SeatBookingLockService {
    LocalDateTime lockSeat(String seatId);
    void unlockSeat(String seatId);
}
