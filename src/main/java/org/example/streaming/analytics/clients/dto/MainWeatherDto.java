package org.example.streaming.analytics.clients.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Api response DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MainWeatherDto {
    Float temp;
    Float feels_like;
    Float temp_min;
    Float temp_max;
    Float pressure;
    Float humidity;
}
