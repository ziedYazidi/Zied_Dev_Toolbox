package com.dawden.kafka.producer.custom.config;


import com.dawden.kafka.producer.custom.config.properties.KafkaProducerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class KafkaProducerConfig {

    @Bean
    @ConfigurationProperties(prefix = "custom.kafka.producer")
    public KafkaProducerProperties kafkaProducerProperties() {
        return new KafkaProducerProperties();
    }

    @Bean(name = "kafkaTemplateCustom")
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(kafkaProducerProperties().getProperties()));
    }
}
