package com.tocaraul.tocaraulserver.repository;

import com.tocaraul.tocaraulserver.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueRepository extends JpaRepository<Venue, Long> {
    Venue findById(int id);
}
