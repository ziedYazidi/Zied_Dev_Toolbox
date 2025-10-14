package com.dawden.kafka.producer.custom.config.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KafkaProducerProperties {
    private Map<String, Object> properties;
}
