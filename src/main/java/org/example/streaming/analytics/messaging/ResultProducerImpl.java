package org.example.streaming.analytics.messaging;

import io.micronaut.scheduling.annotation.Scheduled;
import lombok.extern.slf4j.Slf4j;
import org.example.streaming.analytics.clients.FutureClient;
import org.example.streaming.analytics.clients.dto.MainWeatherDto;
import org.example.streaming.analytics.services.ArggregationService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Slf4j
@Singleton
public class ResultProducerImpl {

    @Inject
    ResultProducer resultProducer;

    @Inject
    FutureClient futureClient;

    @Inject
    ArggregationService arggregationService;

    /**
     * Method to prepare data
     */
    @Scheduled(fixedDelay = "10s")
    public void getData() {
        List<MainWeatherDto> mainWeatherDtoList = futureClient.getStatistics();
        Optional<MainWeatherDto> averageWeatherData = arggregationService.aggregate(mainWeatherDtoList);
        averageWeatherData.ifPresent(this::produceResult);
    }

    /**
     * @param mainWeatherDto Data to be sent to Apache Kafka
     */
    public void produceResult(MainWeatherDto mainWeatherDto) {
        resultProducer.sendResult(mainWeatherDto);
    }
}
