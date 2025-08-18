package com.dawden.kafka.common.config.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
@ConfigurationProperties(prefix = "app.kafka.topics")
public class KafkaAppProperties {
    private Topics producer;
    private Topics consumer;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Topics {
        private String poc;
    }
}
