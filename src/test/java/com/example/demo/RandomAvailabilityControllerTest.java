package com.example.demo;

import com.example.demo.controller.RandomAvailabilityController;
import com.example.demo.service.RandomAvailabilityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

import static org.mockito.Mockito.when;

class RandomAvailabilityControllerTest {

    @InjectMocks
    private RandomAvailabilityController randomAvailabilityController;

    @InjectMocks
    private RandomAvailabilityService randomAvailabilityService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void isVenueAvailable_ShouldReturnRandomValue() {
        when(randomAvailabilityService.isAvailable()).thenReturn(Mono.just(true));

        Mono<Boolean> responseMono = randomAvailabilityController.isVenueAvailable();

        StepVerifier.create(responseMono)
                .expectNext(true)
                .verifyComplete();
    }

    @Test
    void isEquipmentAvailable_ShouldReturnRandomValue() {
        when(randomAvailabilityService.isAvailable()).thenReturn(Mono.just(false));

        Mono<Boolean> responseMono = randomAvailabilityController.isEquipmentAvailable();

        StepVerifier.create(responseMono)
                .expectNext(false)
                .verifyComplete();
    }

    @Test
    void isSpeakerAvailable_ShouldReturnRandomValue() {
        when(randomAvailabilityService.isAvailable()).thenReturn(Mono.just(true));

        Mono<Boolean> responseMono = randomAvailabilityController.isSpeakerAvailable();

        StepVerifier.create(responseMono)
                .expectNext(true)
                .verifyComplete();
    }
}