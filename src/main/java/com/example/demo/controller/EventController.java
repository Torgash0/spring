package com.example.demo.controller;

import com.example.demo.modelDemo.EventRequest;
import com.example.demo.modelDemo.EventResponse;
import com.example.demo.service.EquipmentService;
import com.example.demo.service.SpeakerService;
import com.example.demo.service.VenueService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/events")
public class EventController {

    private final VenueService venueService;
    private final EquipmentService equipmentService;
    private final SpeakerService speakerService;

    public EventController(VenueService venueService, EquipmentService equipmentService, SpeakerService speakerService) {
        this.venueService = venueService;
        this.equipmentService = equipmentService;
        this.speakerService = speakerService;
    }

    @PostMapping("/create")
    public Mono<EventResponse> createEvent(@RequestBody EventRequest eventRequest) {
        String eventDate = eventRequest.getEventDate();

        Mono<Boolean> venueAvailable = venueService.isVenueAvailable(eventDate);
        Mono<Boolean> equipmentAvailable = equipmentService.isEquipmentAvailable(eventDate);
        Mono<Boolean> speakerAvailable = speakerService.isSpeakerAvailable(eventDate);

        return Mono.zip(venueAvailable, equipmentAvailable, speakerAvailable)
                .map(results -> {
                    boolean isVenueAvailable = results.getT1();
                    boolean isEquipmentAvailable = results.getT2();
                    boolean isSpeakerAvailable = results.getT3();

                    boolean canBeCreated = isVenueAvailable && isEquipmentAvailable && isSpeakerAvailable;
                    String message = canBeCreated ? "Event can be created" : "Event cannot be created due to: "
                            + (isVenueAvailable ? "" : "venue unavailable; ")
                            + (isEquipmentAvailable ? "" : "equipment unavailable; ")
                            + (isSpeakerAvailable ? "" : "speaker unavailable; ");
                    return new EventResponse(canBeCreated, message);
                });
    }
}