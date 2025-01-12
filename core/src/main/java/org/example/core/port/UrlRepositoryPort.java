package org.example.core.port;

import org.example.core.domain.Url;

import java.util.Optional;

public interface UrlRepositoryPort {
    void save(Url url);
    Optional<Url> findByShortKey(String shortKey);
}
