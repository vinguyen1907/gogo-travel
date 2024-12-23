package com.uit.se.gogo.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SeatNotAvailableException extends RuntimeException {
    public SeatNotAvailableException(String message) {
        super(message);
    }
}
