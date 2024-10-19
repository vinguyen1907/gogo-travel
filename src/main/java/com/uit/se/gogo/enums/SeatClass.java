package com.uit.se.gogo.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SeatClass {
    BUSINESS,
    ECONOMY,
    FIRST_CLASS
}
