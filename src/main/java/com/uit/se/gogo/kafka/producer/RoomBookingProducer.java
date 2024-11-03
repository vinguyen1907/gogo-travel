package com.uit.se.gogo.kafka.producer;

import com.uit.se.gogo.request.RoomBookingRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class RoomBookingProducer {
    private final KafkaTemplate<String, RoomBookingRequest> roomBookingTemplate;

    public RoomBookingProducer(KafkaTemplate<String, RoomBookingRequest> roomBookingTemplate) {
        this.roomBookingTemplate = roomBookingTemplate;
    }

    public void sendMessage(String topic, RoomBookingRequest message) {
        roomBookingTemplate.send(topic, message.getRoom().getId(), message);
    }
}
