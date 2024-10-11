package com.uit.se.gogo.response;

import com.uit.se.gogo.entity.Location;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AirportResponse {
    String id;
    String code;
    String name;
    Location location;
}