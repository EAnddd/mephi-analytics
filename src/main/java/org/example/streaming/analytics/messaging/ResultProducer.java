package org.example.streaming.analytics.messaging;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.Topic;
import org.example.streaming.analytics.clients.dto.MainWeatherDto;

@KafkaClient
public interface ResultProducer {
    @Topic("streaming-analytics")
    void sendResult(MainWeatherDto result);

}
