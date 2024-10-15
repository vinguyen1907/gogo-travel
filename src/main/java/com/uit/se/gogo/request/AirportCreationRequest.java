package com.uit.se.gogo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AirportCreationRequest {
    final String code;
    final String name;
    final String location;
}
