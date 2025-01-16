package org.example.practice.application.port.out;

import org.example.practice.application.domain.model.Url;

import java.util.Optional;

public interface UrlRepositoryPort {
    void save(Url url);
    boolean existsByShortenedUrl(String url);
    Optional<Url> findByShortenedUrl(String shortenedUrl);
}
