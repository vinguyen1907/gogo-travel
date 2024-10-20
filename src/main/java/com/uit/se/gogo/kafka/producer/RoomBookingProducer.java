package com.uit.se.gogo.kafka.producer;

import com.uit.se.gogo.dto.RoomBookingDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class RoomBookingProducer {
    private final KafkaTemplate<String, RoomBookingDTO> roomBookingTemplate;

    public RoomBookingProducer(KafkaTemplate<String, RoomBookingDTO> roomBookingTemplate) {
        this.roomBookingTemplate = roomBookingTemplate;
    }

    public void sendMessage(String topic, RoomBookingDTO message) {
        roomBookingTemplate.send(topic, message.getRoom().getId(), message);
    }
}
