package com.tocaraul.tocaraulserver.repository;

import com.tocaraul.tocaraulserver.entity.Proposal;
import com.tocaraul.tocaraulserver.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ProposalRepository extends JpaRepository<Proposal, Long> {
    @Query("""
        select p
        from Proposal p
        join fetch p.artist
        join fetch p.event e
        where e.venue = :venue
          and e.date >= :start
          and e.date < :end
        order by e.date asc
    """)
    List<Proposal> findScheduleByVenueAndDateRange(
        @Param("venue") Venue venue,
        @Param("start") LocalDateTime start,
        @Param("end") LocalDateTime end
    );
}
