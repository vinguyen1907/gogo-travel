package com.uit.se.gogo.request;

import java.util.List;

import com.uit.se.gogo.entity.Policy;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AirlineCreationRequest {
    @NotNull(message = "Name is required")
    private String name;
    @NotNull(message = "Image is required")
    private String image;
    private List<Policy> policies;
}
