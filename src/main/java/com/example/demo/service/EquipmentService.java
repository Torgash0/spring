package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Random;
import org.springframework.stereotype.Service;
@Service
public class EquipmentService {

    private final WebClient webClient;

    @Autowired
    public EquipmentService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080/random-availability").build();
    }

    public Mono<Boolean> isEquipmentAvailable(String date) {
        return webClient.get()
                .uri("/equipment")
                .retrieve()
                .bodyToMono(Boolean.class);
    }
}