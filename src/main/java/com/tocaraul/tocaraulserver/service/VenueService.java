package com.tocaraul.tocaraulserver.service;

import com.tocaraul.tocaraulserver.entity.Venue;
import com.tocaraul.tocaraulserver.repository.VenueRepository;
import org.springframework.stereotype.Service;

@Service
public class VenueService {

    private final VenueRepository venueRepository;

    VenueService(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    public Venue findById(int id) {
        return this.venueRepository.findById(id);
    }

    public Venue save(Venue venue) {
        return this.venueRepository.save(venue);
    }

}
