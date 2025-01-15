package org.example.application.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class Url {
    private final String shortKey;
    private final String originalUrl;
    private final LocalDateTime createdAt;
    private final String hashingMethod;
    private final String encodingMethod;

    private int clickCount;

    @Builder
    public Url(String shortKey, String originalUrl, LocalDateTime createdAt, int clickCount, String hashingMethod, String encodingMethod) {
        this.shortKey = shortKey;
        this.originalUrl = originalUrl;
        this.createdAt = createdAt;
        this.clickCount = clickCount;
        this.hashingMethod = hashingMethod;
        this.encodingMethod = encodingMethod;
    }

    public void incrementClickCount() {
        this.clickCount++;
    }
}
