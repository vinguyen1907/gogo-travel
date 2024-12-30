package com.uit.se.gogo.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.uit.se.gogo.entity.Stay;
import com.uit.se.gogo.entity.User;
import com.uit.se.gogo.request.AdminCreateStayRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.uit.se.gogo.dto.StayDTO;
import com.uit.se.gogo.entity.Room;
import com.uit.se.gogo.enums.StayType;
import com.uit.se.gogo.request.SearchStayRequest;
import com.uit.se.gogo.response.DataResponse;
import com.uit.se.gogo.response.PageDataResponse;
import com.uit.se.gogo.service.StayService;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/stays")
@RequiredArgsConstructor
public class StayController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StayController.class);
    private final StayService stayService;

    @GetMapping("/{id}")
    public ResponseEntity<StayDTO> getStayById(@PathVariable String id) {
        LOGGER.info("Receive /api/v1/stays/{}", id);
        var stay = stayService.findById(id);
        var stayDTO = new StayDTO(stay);
        LOGGER.info("Result /api/v1/stays/{}: {}", id, stayDTO);
        return ResponseEntity.ok(stayDTO);
    }

    @GetMapping("/search")
    public ResponseEntity<PageDataResponse<StayDTO>> search(@RequestParam("location_id") @NotNull(message = "Location ID is required") String locationId,
                                               @RequestParam("checkin_date") @NotNull(message = "Check-in date is required") LocalDate checkinDate,
                                               @RequestParam("checkout_date") @NotNull(message = "Check-out date is required") LocalDate checkoutDate,
                                               @RequestParam("rooms") @NotNull(message = "Rooms is required") @Min(value = 1, message = "Rooms must be at least 1") Integer rooms,
                                               @RequestParam("guests") @NotNull(message = "Guests is required") @Min(value = 1, message = "Guests must be at least 1") Integer guests,
                                               @RequestParam(value = "min_price", defaultValue = "0") @Min(value = 0, message = "Min price must be at least 0") Double minPrice,
                                               @RequestParam(value = "max_price", required = false) @Min(value = 0, message = "Max price must be at least 0") Double maxPrice,
                                               @RequestParam(value = "rating", required = false) @Min(value = 0, message = "Rating must be at least 0") @Max(value = 5, message = "Rating cannot exceed 5") Integer rating,
                                               @RequestParam(value = "type", required = false) StayType type,
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
        var stays = stayService.search(request);
        return ResponseEntity.ok(new PageDataResponse<>(stays));
    }

    @GetMapping("/{stayId}/rooms/available")
    public ResponseEntity<DataResponse<List<Room>>> getRooms(
            @PathVariable String stayId,
            @RequestParam("checkin_date") LocalDate checkinDate,
            @RequestParam("checkout_date") LocalDate checkoutDate,
            @RequestParam Integer guests) {
        var rooms = stayService.getAvailableRooms(stayId, checkinDate, checkoutDate, guests);
        return ResponseEntity.ok(new DataResponse<>(rooms));
    }

    @GetMapping("/admin/{stayId}/rooms/all")
    public ResponseEntity<DataResponse<List<Room>>> adminGetAllRoomsOfStay(@PathVariable String stayId) {
        List<Room> rooms = stayService.getAllRooms(stayId);
        return ResponseEntity.ok(new DataResponse<>(rooms));

    }

    @PostMapping("/admin")
    public ResponseEntity<DataResponse<Stay>> createStay(@RequestBody AdminCreateStayRequest request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Stay stay = stayService.create(request, user);
        return ResponseEntity.ok(new DataResponse<>(stay));
    }
}
