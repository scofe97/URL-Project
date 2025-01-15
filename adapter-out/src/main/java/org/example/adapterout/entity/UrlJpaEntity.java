package org.example.adapterout.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.example.application.domain.model.Url;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(
        name = "shortened_url",
        indexes = @Index(name = "idx_short_key", columnList = "short_key", unique = true) // shortKey에 유니크 인덱스 적용
)
public class UrlJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "short_key", nullable = false, unique = true, length = 8)
    private String shortKey;

    @Column(name = "original_url", nullable = false, length = 2083)
    private String originalUrl;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "click_count", nullable = false)
    private int clickCount;

    // 엔터티 → 도메인 변환
    public Url toDomain() {
        return Url.builder()
                .shortKey(shortKey)
                .originalUrl(originalUrl)
                .createdAt(createdAt)
                .clickCount(clickCount)
                .build();
    }

    // 도메인 → 엔터티 변환
    public static UrlJpaEntity fromDomain(Url domain) {
        UrlJpaEntity entity = new UrlJpaEntity();
        entity.shortKey = domain.getShortKey();
        entity.originalUrl = domain.getOriginalUrl();
        entity.createdAt = domain.getCreatedAt();
        entity.clickCount = domain.getClickCount();
        return entity;
    }
}
