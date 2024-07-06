
package com.example.demo.controller;


import com.example.demo.service.RandomAvailabilityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/random-availability")
public class RandomAvailabilityController {

    private final RandomAvailabilityService randomAvailabilityService;

    public RandomAvailabilityController(RandomAvailabilityService randomAvailabilityService) {
        this.randomAvailabilityService = randomAvailabilityService;
    }

    @GetMapping("/venue")
    public Mono<Boolean> isVenueAvailable() {
        return randomAvailabilityService.isAvailable();
    }

    @GetMapping("/equipment")
    public Mono<Boolean> isEquipmentAvailable() {
        return randomAvailabilityService.isAvailable();
    }

    @GetMapping("/speaker")
    public Mono<Boolean> isSpeakerAvailable() {
        return randomAvailabilityService.isAvailable();
    }
}


