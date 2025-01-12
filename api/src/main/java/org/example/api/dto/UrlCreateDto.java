package org.example.api.dto;

public record UrlCreateDto(
        String originalUrl,
        String hashingMethod,
        String encodingMethod
){}
