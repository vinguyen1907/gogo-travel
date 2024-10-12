package com.uit.se.gogo.controller;

import com.uit.se.gogo.entity.Stay;
import com.uit.se.gogo.enums.StayOrderBy;
import com.uit.se.gogo.enums.StayType;
import com.uit.se.gogo.request.SearchStayRequest;
import com.uit.se.gogo.response.DataResponse;
import com.uit.se.gogo.service.StayService;
import jakarta.validation.constraints.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/stays")
@RequiredArgsConstructor
public class StayController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StayController.class);
    private final StayService stayService;

    @GetMapping("/{id}")
    public ResponseEntity<Stay> getStayById(@PathVariable String id) {
        LOGGER.info("Receive /api/v1/stays/{}", id);
        var stay = stayService.findById(id);
        LOGGER.info("Result /api/v1/stays/{}: {}", id, stay);
        return ResponseEntity.ok(stay);
    }

    @GetMapping("/search")
    public ResponseEntity<DataResponse<List<Stay>>> search(@RequestParam("location_id") @NotNull(message = "Location ID is required") String locationId,
                                               @RequestParam("checkin_date") @NotNull(message = "Check-in date is required") Date checkinDate,
                                               @RequestParam("checkout_date") @NotNull(message = "Check-out date is required") Date checkoutDate,
                                               @RequestParam("rooms") @NotNull(message = "Rooms is required") @Min(value = 1, message = "Rooms must be at least 1") Integer rooms,
                                               @RequestParam("guests") @NotNull(message = "Guests is required") @Min(value = 1, message = "Guests must be at least 1") Integer guests,
                                               @RequestParam("min_price") @NotNull(message = "Min price is required") @Min(value = 0, message = "Min price must be at least 0") Double minPrice,
                                               @RequestParam("max_price") @NotNull(message = "Max price is required") @Min(value = 0, message = "Max price must be at least 0") Double maxPrice,
                                               @RequestParam("rating") @NotNull(message = "Rating is required") @Min(value = 0, message = "Rating must be at least 0") @Max(value = 5, message = "Rating cannot exceed 5") Integer rating,
                                               @RequestParam("type") @NotNull(message = "Stay type is required") StayType type,
//                                             @RequestParam("order_by") @NotNull(message = "Order by is required") StayOrderBy orderBy,
                                               @RequestParam(value = "page", defaultValue = "0") @PositiveOrZero Integer page,
                                               @RequestParam(value = "page_size", defaultValue = "10") @Positive Integer pageSize) {
        var request = SearchStayRequest
                .builder()
                .locationId(locationId)
                .checkinDate(checkinDate)
                .checkoutDate(checkoutDate)
                .rooms(rooms)
                .guests(guests)
                .minPrice(minPrice)
                .maxPrice(maxPrice)
                .rating(rating)
                .type(type)
//                .orderBy(orderBy)
                .page(page)
                .pageSize(pageSize)
                .build();
        return ResponseEntity.ok(new DataResponse<>(stayService.search(request)));
    }
}
