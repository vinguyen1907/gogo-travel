package com.uit.se.gogo.kafka.consumer;

import com.uit.se.gogo.request.RoomBookingRequest;
import com.uit.se.gogo.service.RoomBookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class RoomBookingConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoomBookingConsumer.class);

    private final RoomBookingService roomBookingService;

    public RoomBookingConsumer(RoomBookingService roomBookingService) {
        this.roomBookingService = roomBookingService;
    }

    @KafkaListener(topics = "room-booking", groupId = "room-booking-group")
    public void consume(RoomBookingRequest message, @Header(KafkaHeaders.RECEIVED_KEY) String key) {
        LOGGER.info("Received message with key: {}, value: {}", key, message);
        var result = roomBookingService.bookNewRoom(message);
        LOGGER.info("Booked room: {}", result);
        // TODO: return webhook to FE
    }
}
