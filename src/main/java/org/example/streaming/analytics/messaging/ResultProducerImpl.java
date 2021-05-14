package org.example.streaming.analytics.messaging;

import io.micronaut.scheduling.annotation.Scheduled;
import lombok.extern.slf4j.Slf4j;
import org.example.streaming.analytics.clients.FutureClient;
import org.example.streaming.analytics.clients.dto.MainWeatherDto;
import org.example.streaming.analytics.services.ArggregationService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@Singleton
public class ResultProducerImpl {

    @Inject
    ResultProducer resultProducer;

    @Inject
    FutureClient futureClient;

    @Inject
    ArggregationService arggregationService;

    @Scheduled(fixedDelay = "10s")
    public void getData() {
        List<MainWeatherDto> a = futureClient.getStatistics();
        MainWeatherDto b = arggregationService.aggregate(a).get();
        produceResult(b);
    }

    public void produceResult(MainWeatherDto mainWeatherDto) {
        resultProducer.sendResult(mainWeatherDto);
    }
}
