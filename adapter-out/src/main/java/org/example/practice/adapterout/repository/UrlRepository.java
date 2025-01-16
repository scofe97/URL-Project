package org.example.practice.adapterout.repository;

import org.example.practice.adapterout.entity.UrlJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<UrlJpaEntity, Long> {
    boolean existsByShortenedUrl(String shortKey);

    Optional<UrlJpaEntity> findByShortenedUrl(String shortenedUrl);
}
