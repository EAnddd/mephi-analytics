package org.example.streaming.analytics.clients.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * API response DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherDto {
    MainWeatherDto main;
}
