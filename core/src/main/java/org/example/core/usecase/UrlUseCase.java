package org.example.core.usecase;

public interface UrlUseCase {
    String shortenUrl(String originalUrl, String hashingMethod, String encodingMethod);
    String getOriginalUrl(String shortKey);
}