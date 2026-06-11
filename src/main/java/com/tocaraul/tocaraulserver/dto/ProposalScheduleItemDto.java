package com.tocaraul.tocaraulserver.dto;

import com.tocaraul.tocaraulserver.enums.PaymentStatusEnum;
import com.tocaraul.tocaraulserver.enums.ProposalStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProposalScheduleItemDto(
    Long proposalId,
    Long artistId,
    String artistName,
    String artistPhotoUrl,
    String artistGenres,
    Long eventId,
    LocalDateTime eventDate,
    String local,
    String genre,
    String description,
    BigDecimal offeredPrice,
    ProposalStatusEnum status,
    PaymentStatusEnum paymentStatus
) {}
