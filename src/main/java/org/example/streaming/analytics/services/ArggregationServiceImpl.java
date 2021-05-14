package org.example.streaming.analytics.services;

import org.example.streaming.analytics.clients.dto.MainWeatherDto;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

/**
 * Aggregation class
 */
@Singleton
public class ArggregationServiceImpl implements ArggregationService {
    /**
     * @param weatherDtoList List of weather information to get average.
     * @return Average of weather information
     */
    @Override
    public Optional<MainWeatherDto> aggregate(List<MainWeatherDto> weatherDtoList) {
        return weatherDtoList.stream()
                .reduce((a, b) -> MainWeatherDto.builder()
                        .feels_like((a.getFeels_like() + b.getFeels_like())/2)
                        .humidity((a.getHumidity() + b.getHumidity())/2)
                        .pressure((a.getPressure() + b.getPressure())/2)
                        .temp((a.getTemp() + b.getTemp())/2)
                        .temp_max((a.getTemp_max() + b.getTemp_max())/2)
                        .temp_min((a.getTemp_min() + b.getTemp_min())/2)
                        .build() );
    }
}
