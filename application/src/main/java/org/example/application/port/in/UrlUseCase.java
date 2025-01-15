package org.example.application.port.in;

public interface UrlUseCase {
    String shortenUrl(String originalUrl, String hashingMethod);
    String getOriginalUrl(String shortKey);
}