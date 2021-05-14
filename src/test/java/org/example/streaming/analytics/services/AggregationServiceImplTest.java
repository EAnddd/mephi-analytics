package org.example.streaming.analytics.services;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.example.streaming.analytics.clients.dto.MainWeatherDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@MicronautTest
public class AggregationServiceImplTest {

    @Inject
    ArggregationService arggregationService;

    @Test
    public void shouldAggregateData(){
        List<MainWeatherDto> mainWeatherDtoList = new ArrayList<>();
        mainWeatherDtoList.add(new MainWeatherDto(1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f));
        mainWeatherDtoList.add(new MainWeatherDto(2f, 2f, 2f, 2.0f, 2.0f, 2.0f));
        MainWeatherDto result = arggregationService.aggregate(mainWeatherDtoList).get();
        Assertions.assertEquals(result.getFeels_like(), 1.5f);
    }
}
