package com.tocaraul.tocaraulserver.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record NewProposalDto (
    Long artistId,
    LocalDateTime eventDate,
    String local,
    String genre,
    BigDecimal offeredPrice,
    String description
) {}
