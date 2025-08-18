package com.dawden.kafka.producer.defaultt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaTemplateDefaultProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaTemplateDefaultProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topic, String key, String message) {
        kafkaTemplate.send(topic, key, message);
    }

    public void send(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }
}