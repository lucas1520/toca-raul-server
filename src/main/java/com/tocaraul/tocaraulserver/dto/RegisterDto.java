package com.tocaraul.tocaraulserver.dto;

public record RegisterDto (
    String firstName,
    String lastName,
    String email,
    String password,
    String type
) {}
