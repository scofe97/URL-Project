package org.example.practice.application.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class Url {
    private final String shortenedUrl;
    private final String originalUrl;
    private final LocalDateTime createdAt;
    private int clickCount;

    @Builder
    public Url(String shortenedUrl, String originalUrl, LocalDateTime createdAt, int clickCount) {
        this.shortenedUrl = shortenedUrl;
        this.originalUrl = originalUrl;
        this.createdAt = createdAt;
        this.clickCount = clickCount;
    }

    public void incrementClickCount() {
        this.clickCount++;
    }
}
