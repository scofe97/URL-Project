package org.example.application.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UrlAccessLog {
    private final String shortKey;
    private final String userAgent;
    private final String ipAddress;
    private final LocalDateTime accessTime;

    @Builder
    public UrlAccessLog(String shortKey, String userAgent,
                        String ipAddress, LocalDateTime accessTime) {
        this.shortKey = shortKey;
        this.userAgent = userAgent;
        this.ipAddress = ipAddress;
        this.accessTime = accessTime;
    }
}
