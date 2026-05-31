package com.tocaraul.tocaraulserver.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record RegisterEventDto (
    int venueId,
    String genre,
    LocalDateTime date,
    String local,
    String name,
    int maxPeople,
    BigDecimal offeredPrice,
    String imageUrl,
    String description
) { }
