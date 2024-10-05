package com.uit.se.gogo.controller;

import com.uit.se.gogo.entity.Stay;
import com.uit.se.gogo.service.StayService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/stays")
@RequiredArgsConstructor
public class StayController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StayController.class);
    private final StayService stayService;

    @GetMapping("/{id}")
    public ResponseEntity<Stay> getStayById(@PathVariable String id) {
        LOGGER.info("Receive /api/v1/stays/{}", id);
        return ResponseEntity.ok(stayService.findById(id));
    }
}
