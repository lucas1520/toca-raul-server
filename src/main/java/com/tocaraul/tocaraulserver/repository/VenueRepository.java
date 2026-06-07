package com.tocaraul.tocaraulserver.repository;

import com.tocaraul.tocaraulserver.entity.User;
import com.tocaraul.tocaraulserver.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VenueRepository extends JpaRepository<Venue, Long> {
    Optional<Venue> findByUser(User user);
    Venue findById(int id);
}
