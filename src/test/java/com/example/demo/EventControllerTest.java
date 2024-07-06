package com.example.demo;

import com.example.demo.controller.EventController;
import com.example.demo.modelDemo.EventRequest;
import com.example.demo.modelDemo.EventResponse;
import com.example.demo.service.EquipmentService;
import com.example.demo.service.SpeakerService;
import com.example.demo.service.VenueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

class EventControllerTest {

    @Mock
    private VenueService venueService;

    @Mock
    private EquipmentService equipmentService;

    @Mock
    private SpeakerService speakerService;

    @InjectMocks
    private EventController eventController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createEvent_WhenAllAvailable_ShouldReturnCanBeCreated() {
        when(venueService.isVenueAvailable("2024-08-01")).thenReturn(Mono.just(true));
        when(equipmentService.isEquipmentAvailable("2024-08-01")).thenReturn(Mono.just(true));
        when(speakerService.isSpeakerAvailable("2024-08-01")).thenReturn(Mono.just(true));

        Mono<EventResponse> responseMono = eventController.createEvent(new EventRequest());

        StepVerifier.create(responseMono)
                .expectNextMatches(response -> response.isCanBeCreated() && response.getMessage().equals("Event can be created"))
                .verifyComplete();
    }

    @Test
    void createEvent_WhenAnyUnavailable_ShouldReturnCannotBeCreated() {
        when(venueService.isVenueAvailable("2024-08-01")).thenReturn(Mono.just(false));
        when(equipmentService.isEquipmentAvailable("2024-08-01")).thenReturn(Mono.just(true));
        when(speakerService.isSpeakerAvailable("2024-08-01")).thenReturn(Mono.just(true));

        Mono<EventResponse> responseMono = eventController.createEvent(new EventRequest());

        StepVerifier.create(responseMono)
                .expectNextMatches(response -> !response.isCanBeCreated() && response.getMessage().contains("venue unavailable"))
                .verifyComplete();
    }
}