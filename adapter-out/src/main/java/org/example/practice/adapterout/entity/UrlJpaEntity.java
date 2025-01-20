package org.example.practice.adapterout.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.example.practice.application.domain.model.Url;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(
        name = "URL",
        indexes = @Index(name = "idx_shortened_url", columnList = "shortened_url", unique = true) // shortened_url 유니크 인덱스 적용
)
public class UrlJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "shortened_url", nullable = false, unique = true, length = 8)
    private String shortenedUrl;

    @Column(name = "original_url", nullable = false, length = 2083)
    private String originalUrl;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "click_count", nullable = false)
    private int clickCount;

    // 엔터티 → 도메인 변환
    public Url toDomain() {
        return Url.builder()
                .shortenedUrl(shortenedUrl)
                .originalUrl(originalUrl)
                .createdAt(createdAt)
                .clickCount(clickCount)
                .build();
    }

    // 도메인 → 엔터티 변환
    public static UrlJpaEntity fromDomain(Url domain) {
        UrlJpaEntity entity = new UrlJpaEntity();
        entity.shortenedUrl = domain.getShortenedUrl();
        entity.originalUrl = domain.getOriginalUrl();
        entity.createdAt = domain.getCreatedAt();
        entity.clickCount = domain.getClickCount();
        return entity;
    }
}
