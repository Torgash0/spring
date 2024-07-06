package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Random;

@Service
public class VenueService {

    private final WebClient webClient;

    @Autowired
    public VenueService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080/random-availability").build();
    }

    public Mono<Boolean> isVenueAvailable(String date) {
        return webClient.get()
                .uri("/venue")
                .retrieve()
                .bodyToMono(Boolean.class);
    }
}
