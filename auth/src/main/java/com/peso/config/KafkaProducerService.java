package com.peso.config;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String,String> kafkaTemplate;

    public void produceNotification(String notificationMsg){
        kafkaTemplate.send("notifications-service", notificationMsg);
    }
}
