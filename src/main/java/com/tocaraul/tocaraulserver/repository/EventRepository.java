package com.tocaraul.tocaraulserver.repository;

import com.tocaraul.tocaraulserver.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

}
