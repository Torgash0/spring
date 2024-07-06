package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Random;

@Service
public class SpeakerService {

    private final WebClient webClient;

    @Autowired
    public SpeakerService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080/random-availability").build();
    }

    public Mono<Boolean> isSpeakerAvailable(String date) {
        return webClient.get()
                .uri("/speaker")
                .retrieve()
                .bodyToMono(Boolean.class);
    }
}