package com.example.demo.service;


import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Random;

@Service
public class RandomAvailabilityService {
    private final Random random = new Random();

    public Mono<Boolean> isAvailable() {
        return Mono.just(random.nextBoolean());
    }
}
