package org.example.streaming.analytics.services;

import org.example.streaming.analytics.clients.dto.MainWeatherDto;

import java.util.List;
import java.util.Optional;

public interface ArggregationService {
    public Optional<MainWeatherDto> aggregate(List<MainWeatherDto> weatherDtoList);
}
