package org.example.streaming.analytics.controllers;

import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.RequestAttribute;
import org.example.streaming.analytics.clients.FutureClient;
import org.example.streaming.analytics.messaging.ResultProducerImpl;
import org.example.streaming.analytics.services.ArggregationService;

import javax.inject.Inject;

/**
 * Controller for initializing scheduled application
 */
@Controller
public class StartController {

    @Inject
    ResultProducerImpl resultProducer;

    @Inject
    FutureClient futureClient;

    @Inject
    ArggregationService arggregationService;


    /**
     * @param token Token from api.openweathermap.org
     */
    @Post(value = "/start")
    @Consumes("application/x-www-form-urlencoded")
    public void send(@RequestAttribute("token") String token){
        futureClient.createUris(token);
    }

}
