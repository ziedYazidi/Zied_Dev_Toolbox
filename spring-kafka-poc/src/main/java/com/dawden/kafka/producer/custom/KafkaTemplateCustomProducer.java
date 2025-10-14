package com.dawden.kafka.producer.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaTemplateCustomProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaTemplateCustomProducer(@Qualifier("kafkaTemplateCustom") KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topic, String key, String message) {
        kafkaTemplate.send(topic, key, message);
    }
}
