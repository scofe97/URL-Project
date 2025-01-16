package org.example.practice.application.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class Url {
    private final String shortened_url;
    private final String originalUrl;
    private final LocalDateTime createdAt;
    private int clickCount;

    @Builder
    public Url(String shortened_url, String originalUrl, LocalDateTime createdAt, int clickCount) {
        this.shortened_url = shortened_url;
        this.originalUrl = originalUrl;
        this.createdAt = createdAt;
        this.clickCount = clickCount;
    }

    public void incrementClickCount() {
        this.clickCount++;
    }
}
