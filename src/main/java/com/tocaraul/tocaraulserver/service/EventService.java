package com.tocaraul.tocaraulserver.service;

import com.tocaraul.tocaraulserver.entity.Event;
import com.tocaraul.tocaraulserver.repository.EventRepository;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    private final EventRepository eventRepository;

    EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event save(Event event) {
        return this.eventRepository.save(event);
    }
}
