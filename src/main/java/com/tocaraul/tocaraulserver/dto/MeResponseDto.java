package com.tocaraul.tocaraulserver.dto;

import com.tocaraul.tocaraulserver.entity.Artist;
import com.tocaraul.tocaraulserver.entity.Venue;

public record MeResponseDto(
        Long id,
        String firstName,
        String lastName,
        String email,
        com.tocaraul.tocaraulserver.enums.UserTypeEnum type,
        Artist artist,
        Venue venue
) {}