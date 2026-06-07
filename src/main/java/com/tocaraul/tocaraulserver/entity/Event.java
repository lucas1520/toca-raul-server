package com.tocaraul.tocaraulserver.entity;

import com.tocaraul.tocaraulserver.dto.RegisterEventDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "events")
public class Event {

    public static Event from(RegisterEventDto registerEventDto, Venue venue) {
        Event event = new Event();

        event.setVenue(venue);
        event.setGenre(registerEventDto.genre());
        event.setDate(registerEventDto.date());
        event.setLocal(registerEventDto.local());
        event.setDescription(registerEventDto.description());

        return event;
    }

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venue_id", nullable = false)
    private Venue venue;

    @Getter
    @Setter
    @Column(nullable = false)
    private String genre;

    @Getter
    @Setter
    @Column(nullable = false)
    private LocalDateTime date;

    @Getter
    @Setter
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Getter
    @Setter
    @Column(nullable = false)
    private String local;

    @Getter
    @Setter
    @Column(nullable = true)
    private String imagesUrl;

    @Getter
    @Setter
    @Column(nullable = true)
    private String description;

    @Getter
    @Setter
    @Column(scale = 2)
    private BigDecimal offeredPrice;

    @Getter
    @Setter
    @Column(nullable = true)
    private int maxPeople;

}
