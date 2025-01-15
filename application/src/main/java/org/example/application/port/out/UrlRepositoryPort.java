package org.example.application.port.out;

import org.example.application.domain.model.Url;

import java.util.Optional;

public interface UrlRepositoryPort {
    void save(Url url);
    boolean existsByShortenKey(String url);
    Optional<Url> findByShortKey(String shortKey);
}
