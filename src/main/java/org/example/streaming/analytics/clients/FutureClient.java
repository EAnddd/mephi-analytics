package org.example.streaming.analytics.clients;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.uri.UriBuilder;
import io.reactivex.Flowable;
import org.example.streaming.analytics.clients.dto.CityNames;
import org.example.streaming.analytics.clients.dto.MainWeatherDto;
import org.example.streaming.analytics.clients.dto.WeatherDto;

import javax.inject.Singleton;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Client for connecting to weather open api
 */
@Singleton
public class FutureClient {

    private final RxHttpClient httpClient;
    private List<URI> uris = new ArrayList<>();

    public FutureClient(@Client("http://api.openweathermap.org") RxHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * @param token Access token from api.openweathermap.org
     *              Method to initialize which cities to get data about
     */
    public void createUris(String token) {
        for(CityNames city: CityNames.values()){
            this.uris.add(UriBuilder.of("/data/2.5/weather?q=" + city.name() + "&appid=" + token + "&units=metric")
                    .build());
        }
    }

    /**
     * @return Method to get information about the cities initialized in createUris(String token) method
     */
    public List<MainWeatherDto> getStatistics() {
        List<MainWeatherDto> results = new ArrayList<>();
        for(URI uri: uris) {
            HttpRequest<?> req = HttpRequest.GET(uri);
            Flowable<WeatherDto> flowable = httpClient.retrieve(req, WeatherDto.class);
            results.add(flowable.firstElement().blockingGet().getMain());
        }
        return results;
    }
}
