package com.tocaraul.tocaraulserver.dto;

public record RegisterDto (
    String firstName,
    String lastName,
    String email,
    String password,
    String type,
    String venueName,
    String venueLocal,
    String venueCity,
    String venuePhotosUrl
) {}
