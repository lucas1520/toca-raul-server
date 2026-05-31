package com.tocaraul.tocaraulserver.controller;

import com.tocaraul.tocaraulserver.dto.RegisterEventDto;
import com.tocaraul.tocaraulserver.entity.Event;
import com.tocaraul.tocaraulserver.entity.Venue;
import com.tocaraul.tocaraulserver.service.EventService;
import com.tocaraul.tocaraulserver.service.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;
    private final VenueService venueService;

    EventController(EventService eventService, VenueService venueService) {
        this.eventService = eventService;
        this.venueService = venueService;
    }

    @PostMapping("/register")
    public ResponseEntity<Event> registerEvent(@RequestBody RegisterEventDto registerEventDto) {
        Venue venue = this.venueService.findById(registerEventDto.venueId());

        if (venue == null) {
            return ResponseEntity.badRequest().build();
        }

        Event newEvent = Event.from(registerEventDto, venue);

        Event saved = this.eventService.save(newEvent);

        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}
