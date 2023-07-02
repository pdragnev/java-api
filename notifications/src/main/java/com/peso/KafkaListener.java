package com.peso;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaListener {

    @org.springframework.kafka.annotation.KafkaListener(topics = "notifications-service",
    groupId = "groupId1")
    void listener(String data) {
        log.info("Consumed notification" + data);
    }
}
