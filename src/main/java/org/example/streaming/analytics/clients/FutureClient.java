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

@Singleton
public class FutureClient {

    private final RxHttpClient httpClient;
    private List<URI> uris = new ArrayList<>();

    public FutureClient(@Client("http://api.openweathermap.org") RxHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public void createUris(String token) {
        for(CityNames city: CityNames.values()){
            this.uris.add(UriBuilder.of("/data/2.5/weather?q=" + city.name() + "&appid=" + token + "&units=metric")
                    .build());
        }
    }

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
