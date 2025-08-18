package com.dawden.runner;

import com.dawden.kafka.common.config.properties.KafkaAppProperties;
import com.dawden.kafka.producer.custom.KafkaTemplateCustomProducer;
import com.dawden.kafka.producer.defaultt.KafkaTemplateDefaultProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SpringKafkaPocCommandLineRunner implements CommandLineRunner {
    private final KafkaAppProperties kafkaAppProperties;
    private final KafkaTemplateDefaultProducer kafkaTemplateDefaultProducer;
    private final KafkaTemplateCustomProducer kafkaTemplateCustomProducer;

    @Autowired
    public SpringKafkaPocCommandLineRunner(
            KafkaAppProperties kafkaAppProperties, KafkaTemplateDefaultProducer kafkaTemplateDefaultProducer,
            KafkaTemplateCustomProducer kafkaTemplateCustomProducer
    ) {
        this.kafkaAppProperties = kafkaAppProperties;
        this.kafkaTemplateDefaultProducer = kafkaTemplateDefaultProducer;
        this.kafkaTemplateCustomProducer = kafkaTemplateCustomProducer;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Running commandLineRunner after startup");
        kafkaTemplateDefaultProducer.send(kafkaAppProperties.getProducer().getPoc(), "Key1", "Produced from Kafka Template default producer");
        kafkaTemplateCustomProducer.send(kafkaAppProperties.getProducer().getPoc(), "Key2", "Produced from Kafka Template custom producer");
    }
}
