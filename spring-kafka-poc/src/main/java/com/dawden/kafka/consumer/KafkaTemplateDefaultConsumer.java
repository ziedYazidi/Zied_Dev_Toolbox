package com.dawden.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaTemplateDefaultConsumer {

    @KafkaListener(topics = "#{@kafkaAppProperties.consumer.poc}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(String message) {
        System.out.println("Consumed message: " + message);
    }
}
