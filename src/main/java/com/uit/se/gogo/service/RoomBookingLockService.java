package com.uit.se.gogo.service;

import java.time.LocalDateTime;

public interface RoomBookingLockService {
    LocalDateTime lockRoom(String roomId);
    void unlockRoom(String roomId);
}
