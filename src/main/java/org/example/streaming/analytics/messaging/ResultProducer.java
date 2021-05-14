package org.example.streaming.analytics.messaging;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.Topic;
import org.example.streaming.analytics.clients.dto.MainWeatherDto;

/**
 * Interface for initializing producer binding to Apache Kafka
 */
@KafkaClient
public interface ResultProducer {
    @Topic("streaming-analytics")
    void sendResult(MainWeatherDto result);

}
