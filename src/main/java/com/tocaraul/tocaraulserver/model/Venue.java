package com.tocaraul.tocaraulserver.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "venues")
public class Venue {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Getter
    @Setter
    @Column(nullable = false)
    private String name;

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
    @Column(nullable = false)
    private String city;

    @Setter
    @Getter
    @Column(nullable = false)
    private String genre;

    @Setter
    @Getter
    @Column(nullable = false)
    private String photosUrl;
}
