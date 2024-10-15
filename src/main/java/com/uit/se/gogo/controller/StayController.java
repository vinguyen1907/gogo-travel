package com.uit.se.gogo.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uit.se.gogo.entity.Room;
import com.uit.se.gogo.entity.Stay;
import com.uit.se.gogo.request.SearchStayRequest;
import com.uit.se.gogo.response.DataResponse;
import com.uit.se.gogo.service.StayService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

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
    public ResponseEntity<DataResponse<List<Stay>>> search(@RequestBody @Valid SearchStayRequest request) {
        return ResponseEntity.ok(new DataResponse<>(stayService.search(request)));
    }

    @GetMapping("/{stayId}/rooms/available")
    public ResponseEntity<DataResponse<List<Room>>> getRooms(
            @PathVariable String stayId,
            @RequestParam("checkin_date") Date checkinDate,
            @RequestParam("checkout_date") Date checkoutDate,
            @RequestParam Integer guests) {
        var rooms = stayService.getAvailableRooms(stayId, checkinDate, checkoutDate, guests);
        return ResponseEntity.ok(new DataResponse<>(rooms));
    }
}
