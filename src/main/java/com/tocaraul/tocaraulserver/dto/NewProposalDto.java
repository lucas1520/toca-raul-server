package com.tocaraul.tocaraulserver.dto;

import java.math.BigDecimal;

public record NewProposalDto (
    Long artistId,
    Long venueId,
    BigDecimal offeredPrice
) {}
