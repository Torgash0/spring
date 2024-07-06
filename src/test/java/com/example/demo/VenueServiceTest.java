package com.example.demo;

import com.example.demo.service.VenueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

class VenueServiceTest {

    private WebClient.Builder webClientBuilder;

    private WebClient webClient;

    @InjectMocks
    private VenueService venueService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        webClientBuilder = mock(WebClient.Builder.class);
        webClient = mock(WebClient.class);
        when(webClientBuilder.baseUrl(anyString())).thenReturn(webClientBuilder);
        when(webClientBuilder.build()).thenReturn(webClient);
        venueService = new VenueService(webClientBuilder);
    }

    @Test
    void isVenueAvailable_ShouldReturnTrue() {
        WebClient.RequestHeadersUriSpec requestHeadersUriSpec = mock(WebClient.RequestHeadersUriSpec.class);
        WebClient.RequestHeadersSpec requestHeadersSpec = mock(WebClient.RequestHeadersSpec.class);
        WebClient.ResponseSpec responseSpec = mock(WebClient.ResponseSpec.class);

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri("/venue")).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Boolean.class)).thenReturn(Mono.just(true));

        Mono<Boolean> responseMono = venueService.isVenueAvailable("2024-08-01");

        StepVerifier.create(responseMono)
                .expectNext(true)
                .verifyComplete();
    }

    @Test
    void isVenueAvailable_ShouldReturnFalse() {
        WebClient.RequestHeadersUriSpec requestHeadersUriSpec = mock(WebClient.RequestHeadersUriSpec.class);
        WebClient.RequestHeadersSpec requestHeadersSpec = mock(WebClient.RequestHeadersSpec.class);
        WebClient.ResponseSpec responseSpec = mock(WebClient.ResponseSpec.class);

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri("/venue")).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Boolean.class)).thenReturn(Mono.just(false));

        Mono<Boolean> responseMono = venueService.isVenueAvailable("2024-08-01");

        StepVerifier.create(responseMono)
                .expectNext(false)
                .verifyComplete();
    }
}