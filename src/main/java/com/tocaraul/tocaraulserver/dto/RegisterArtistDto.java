package com.tocaraul.tocaraulserver.dto;

import java.math.BigDecimal;

public record RegisterArtistDto (
    String name,
    String about,
    String photoUrl,
    String genres,
    String samplesUrl,
    BigDecimal startPrice,
    int userId
) {}
