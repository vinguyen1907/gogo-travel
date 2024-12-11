package com.uit.se.gogo.service;

public interface RoomBookingLockService {
    void lockRoom(String roomId);
    void unlockRoom(String roomId);
}
